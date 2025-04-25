package com.yoonlee3.diary.diary;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.like.LikeService;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DiaryController {

	@Autowired
	DiaryService diaryService;
	@Autowired
	UserService userService;
	@Autowired
	Diary_gptService api;
	@Autowired
	LikeService likeService;
	@Autowired
	JoinToGroupService joinToGroupService;

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

	@GetMapping("/diary/list")
	public String list(Model model) {
		model.addAttribute("list", diaryService.findAll());
		return "diary/list";
	}

	@GetMapping("/diary/detail/{id}")
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
		return "diary/detail";
	}

	@GetMapping("/diary/insert")
	public String insert_get(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "diary/write";
	}

	@PostMapping("/diary/insert")
	public String insert_post(Diary diary, Principal principal) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		diary.setUser(user);
		diaryService.insert(diary);
		return "redirect:/diary/list";
	}

	@PostMapping("/diary/emoji")
	@ResponseBody
	public Map<String, String> getSummary(@RequestBody Map<String, String> request) {
		String diary_content = request.get("content");
		String emoji = api.getAIResponse(diary_content);
		Map<String, String> result = new HashMap<>();
		result.put("emoji", emoji);
		return result;
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
		if (diary.getUser().getEmail().equals(principal.getName())) {
			model.addAttribute("id", id);
			return "diary/delete";
		} else {
			rttr.addFlashAttribute("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
			return "redirect:/diary/list";
		}
	}

	@PostMapping("/diary/delete")
	public String delete_post(Diary diary, RedirectAttributes rttr) {
		String msg = "fail";
		if (diaryService.delete(diary) > 0) {
			msg = "글삭제성공!";
		}
		rttr.addFlashAttribute("msg", msg);
		diaryService.delete(diary); // ## 글삭제기능
		return "redirect:/diary/list";
	}
}
