package com.yoonlee3.diary.diary;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.like.LikeService;
=======
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DiaryController {
<<<<<<< HEAD
	@Autowired DiaryService service;
	@Autowired UserRepository userRepository;
	@Autowired Diary_gptService api;
	@Autowired LikeService likeService;
	
=======
	
	@Autowired
	DiaryService service;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Diary_gptService api;

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
<<<<<<< HEAD
		    User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 없습니다."));
		    model.addAttribute("nickname", user.getUsername());
		} else {
		        model.addAttribute("nickname", "Guest");
		    }
	}
	
	@GetMapping("/diary/list")
	public String list(Model model){
		model.addAttribute("list" , service.findAll());
		return "diary/list";
	}
	
	@GetMapping("/diary/detail/{id}")
	public String detail( @PathVariable Long id, Model model, Principal principal){
		model.addAttribute("dto" , service.find(id));
		
	    long likeCount = likeService.getLikeCount(id);
	    model.addAttribute("likeCount", likeCount);
		
		if (principal != null) {
	           String email = principal.getName();
	           User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 없습니다."));
	           boolean isLiked = likeService.isLiked(id, user.getId());
	           model.addAttribute("isLiked", isLiked); // 좋아요 여부 추가
	    } else {
	            model.addAttribute("isLiked", false); // 비로그인 시 좋아요 상태는 false
	      }
		return "diary/detail";
	}
	
	@GetMapping("/diary/insert")
	public String insert_get(Principal principal, Model model){ 
		model.addAttribute("username" , principal.getName());
		return "diary/write"; 
	} 
	
	@PostMapping("/diary/insert")
	public String insert_post(Diary diary, Principal principal){
	    String email = principal.getName();
	    User user = userRepository.findByEmail(email).orElseThrow();
	    diary.setUser(user);
	    service.insert(diary);
	    return "redirect:/diary/list";
=======
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 없습니다."));
			model.addAttribute("nickname", user.getUsername());
		} else {
			model.addAttribute("nickname", "Guest");
		}
	}

	@GetMapping("/diary/list")
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "diary/list";
	}

	@GetMapping("/diary/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("dto", service.find(id));
		return "diary/detail";
	}

	@GetMapping("/diary/insert")
	public String insert_get(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "diary/write";
	}

	@PostMapping("/diary/insert")
	public String insert_post(Diary diary, Principal principal, Model model ) {
		String email = principal.getName();
		User user = userRepository.findByEmail(email).orElseThrow();
		diary.setUser(user);
		
		String emoji = api.getAIResponse(diary.getDiary_content());
		model.addAttribute("emoji", emoji);
		
		service.insert(diary);
		return "redirect:/diary/list";
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
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
<<<<<<< HEAD
	
	@GetMapping("/diary/update/{id}")
	public String update_get(@PathVariable Long id , Principal principal, Model model, RedirectAttributes rttr){ 
		Diary diary = service.update_view(id); //## 수정할 글 가져오기
		if (diary.getUser().getEmail().equals(principal.getName())) {
			model.addAttribute("dto", diary); // 수정할 일기 가져오기
			return "diary/edit"; 
		} else {
			rttr.addFlashAttribute("msg", "본인이 작성한 글만 수정할 수 있습니다.");
			return "redirect:/diary/list"; 
		}
	}
	
	@PostMapping("/diary/update")
	public String update_post( Diary diary , RedirectAttributes rttr ){
		String msg = "fail";
		if( service.update(diary) > 0 ) { msg="글수정완료!"; }
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/diary/detail/" + diary.getId();  //## 글수정기능
	}
	
	@GetMapping("/diary/delete/{id}")
	public String delete_get(@PathVariable Long id , Principal principal, Model model, RedirectAttributes rttr){
		Diary diary = service.find(id);
		if (diary.getUser().getEmail().equals(principal.getName())) {
			model.addAttribute("id", id);
			return "diary/delete"; 
		} else {
			rttr.addFlashAttribute("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
			return "redirect:/diary/list"; 
		}
	}
	
	@PostMapping("/diary/delete")
	public String delete_post( Diary diary , RedirectAttributes rttr ){
		String msg = "fail";
		if( service.delete(diary) > 0 ) { msg="글삭제성공!"; }
		rttr.addFlashAttribute("msg",msg);
		service.delete(diary); //## 글삭제기능
		return "redirect:/diary/list"; 
=======

	@GetMapping("/diary/update/{id}")
	public String update_get(@PathVariable Long id, Model model) {
		model.addAttribute("dto", service.update_view(id)); // ## 수정할 글 가져오기
		return "diary/edit";
	}

	@PostMapping("/diary/update")
	public String update_post(Diary diary, RedirectAttributes rttr) {
		String msg = "fail";
		if (service.update(diary) > 0) {
			msg = "글수정완료!";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/diary/detail/" + diary.getId(); // ## 글수정기능
	}

	@GetMapping("/diary/delete/{id}")
	public String delete_get(@PathVariable Long id, Model model) {
		model.addAttribute("id", id);
		return "diary/delete";
	}

	@PostMapping("/diary/delete")
	public String delete_post(Diary diary, RedirectAttributes rttr) {
		String msg = "fail";
		if (service.delete(diary) > 0) {
			msg = "글삭제성공!";
		}
		rttr.addFlashAttribute("msg", msg);
		service.delete(diary); // ## 글삭제기능
		return "redirect:/diary/list";
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}
}
