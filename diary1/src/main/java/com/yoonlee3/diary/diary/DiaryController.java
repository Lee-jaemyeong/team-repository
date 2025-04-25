package com.yoonlee3.diary.diary;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.group.GroupService;
import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.groupDiary.GroupDiaryService;
import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.like.LikeService;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.openScope.OpenScopeService;
import com.yoonlee3.diary.template.Template;
import com.yoonlee3.diary.template.TemplateService;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DiaryController {

	@Autowired DiaryService diaryService;
	@Autowired UserService userService;
	@Autowired Diary_gptService api;
	@Autowired LikeService likeService;
	@Autowired OpenScopeService openScopeService;
	@Autowired TemplateService templateService;
	@Autowired JoinToGroupService joinToGroupService;
	@Autowired GroupService groupService;
	@Autowired GroupDiaryService groupDiaryService;

	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			model.addAttribute("nickname", user.getUsername());
			model.addAttribute("user", user);
			
			Set<YL3Group> groups = joinToGroupService.findGroupById(user.getId());
	        model.addAttribute("groups", groups);
	    } else {
	        model.addAttribute("nickname", "Guest");
	        model.addAttribute("groups", Collections.emptySet());
	    }
	}

	@GetMapping("/main")
	public String mainList(Model model) {
		model.addAttribute("list", diaryService.findAll());
		model.addAttribute("isMainPage", true);
	    return "mainTemplate/main";
	}
	
	/*
	@GetMapping("/diary/list")
	@ResponseBody
	public List<Diary> getDiaryList() {
	    List<Diary> diaryList = diaryService.findAll();
	    return diaryList;
	}
	*/

	@GetMapping("/mainTemplate/detail/{id}")
	public String detail(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("dto", diaryService.findById(id));

		long likeCount = likeService.getLikeCount(id);
		model.addAttribute("likeCount", likeCount);

		if (principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			boolean isLiked = likeService.isLiked(id, user.getId());
			model.addAttribute("isLiked", isLiked); // 좋아요 여부 추가
		} else {
			model.addAttribute("isLiked", false); // 비로그인 시 좋아요 상태는 false
		}
		return "mainTemplate/detail";
	}

	@GetMapping("/diary/insert")
	public String insert_get(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "mainTemplate/write";
	}

	@PostMapping("/diary/insert")
	public String insert_post(Diary diary, @RequestParam Long open_scope_id, @RequestParam Long template_id, Principal principal) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		diary.setUser(user);
		
		OpenScope openScope = openScopeService.findOpenScopeById(open_scope_id);
		diary.setOpenScope(openScope);
		Template template = templateService.findTempalteById(template_id);
		diary.setTemplate(template);
		
		diaryService.insert(diary);
		return "redirect:/main";
	}

	/// 그룹 다이어리 쓰기
	@PostMapping("group/{id}/diary/insert")
	public String insertGroupDiary_post(Diary diary, Principal principal, @PathVariable("id") Long group_id) {
		// 유저찾기
		String email = principal.getName();
		User user = userService.findByEmail(email);
		
		// 다이어리 저장
		diary.setUser(user);
		Diary savediary = diaryService.insert(diary);
		
		// 그룹 다이어리로 저장
		YL3Group group = groupService.findById(group_id);
		groupDiaryService.insertGroupDiary(group, savediary);
		return "redirect:/group/group/" + group_id;
	}
	
	@PostMapping("/diary/emoji")
	@ResponseBody
	public Map<String, String> getSummary(@RequestBody Map<String, String> request) {
		String diaryContent = request.get("content");
		String emoji = api.getAIResponse(diaryContent);
		Map<String, String> result = new HashMap<>();
		result.put("emoji", emoji);
		return result;
	}
	
	@GetMapping("group/groupDiaryDetail/{id}")
	public String groupDiaryDetail_get(@PathVariable("id") Long diary_id, Model model, Principal principal) {
		model.addAttribute("dto", diaryService.findById(diary_id));

		long likeCount = likeService.getLikeCount(diary_id);
		model.addAttribute("likeCount", likeCount);

		if (principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			boolean isLiked = likeService.isLiked(diary_id, user.getId());
			model.addAttribute("isLiked", isLiked); // 좋아요 여부 추가
		} else {
			model.addAttribute("isLiked", false); // 비로그인 시 좋아요 상태는 false
		}
		return "diary/detail";
	}

	@GetMapping("/diary/update/{id}")
	public String update_get(@PathVariable Long id, Principal principal, Model model, RedirectAttributes rttr) {
		Diary diary = diaryService.update_view(id); // ## 수정할 글 가져오기
		if (diary.getUser().getEmail().equals(principal.getName())) {
			model.addAttribute("dto", diary); // 수정할 일기 가져오기
			return "diary/edit";
		} else {
			rttr.addFlashAttribute("msg", "본인이 작성한 글만 수정할 수 있습니다.");
			return "redirect:/diary/list";
		}
	}

	@PostMapping("/diary/update")
	public String update_post(Diary diary, RedirectAttributes rttr) {
		String msg = "fail";
		if (diaryService.update(diary) > 0) {
			msg = "글수정완료!";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/diary/detail/" + diary.getId(); // ## 글수정기능
	}

	@GetMapping("/diary/delete/{id}")
	public String delete_get(@PathVariable Long id, Principal principal, Model model, RedirectAttributes rttr) {
	    Diary diary = diaryService.findById(id);
	    
	    // 다이어리가 존재하고, 현재 로그인한 사용자가 작성자와 일치하는지 확인
	    if (diary == null) {
	        rttr.addFlashAttribute("msg", "다이어리를 찾을 수 없습니다.");
	        return "redirect:/main";
	    }
	    
	    if (diary.getUser().getEmail().equals(principal.getName())) {
	        // 삭제 요청 전에 확인 메시지 표시
	        model.addAttribute("diary", diary); // 삭제할 다이어리 정보를 모델에 추가
	        return "/diary/delete/{id}"; // 삭제 확인 페이지로 이동
	    } else {
	        rttr.addFlashAttribute("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
	        return "redirect:/main";
	    }
	}

	@PostMapping("/diary/delete/{id}")
	public String delete_post(@PathVariable Long id, Principal principal, RedirectAttributes rttr) {
	    Diary diary = diaryService.findById(id);

	    // 다이어리 존재 여부와 로그인한 사용자가 작성자인지 확인
	    if (diary == null) {
	        rttr.addFlashAttribute("msg", "다이어리를 찾을 수 없습니다.");
	        return "redirect:/main";
	    }

	    if (diary.getUser().getEmail().equals(principal.getName())) {
	        // 삭제 처리
	        if (diaryService.delete(diary) > 0) {
	            rttr.addFlashAttribute("msg", "글삭제 성공!");
	        } else {
	            rttr.addFlashAttribute("msg", "글삭제 실패!");
	        }
	    } else {
	        rttr.addFlashAttribute("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
	    }
	    
	    return "redirect:/main";
	}
	
}
