package com.yoonlee3.diary.diary;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
=======
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.like.LikeService;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DiaryController {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	@Autowired DiaryService service;
	@Autowired UserRepository userRepository;
	@Autowired Diary_gptService api;
	@Autowired LikeService likeService;
	
=======
	
=======

>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	@Autowired
	DiaryService service;
	@Autowired
	UserService userService;
	@Autowired
	Diary_gptService api;
	@Autowired
	LikeService likeService;

<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

	@Autowired
	DiaryService service;
	@Autowired
	UserService userService;
	@Autowired
	Diary_gptService api;
	@Autowired
	LikeService likeService;

>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		    User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("사용자 정보가 없습니다."));
		    model.addAttribute("nickname", user.getUsername());
=======
			User user = userService.findByEmail(email);
			model.addAttribute("nickname", user.getUsername());
>>>>>>> 64f87d4 (0422)
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
	public String detail(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("dto", service.findById(id));

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
<<<<<<< HEAD
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
=======
			User user = userService.findByEmail(email);
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
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
	public String detail(@PathVariable Long id, Model model, Principal principal) {
		model.addAttribute("dto", service.findById(id));

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
		service.insert(diary);
		return "redirect:/diary/list";
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
	public String insert_get(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "diary/write";
>>>>>>> 64f87d4 (0422)
	}

	@PostMapping("/diary/insert")
	public String insert_post(Diary diary, Principal principal) {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		diary.setUser(user);
		service.insert(diary);
		return "redirect:/diary/list";
	}

=======
	}

>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
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
<<<<<<< HEAD
<<<<<<< HEAD
	
=======

>>>>>>> 64f87d4 (0422)
	@GetMapping("/diary/update/{id}")
	public String update_get(@PathVariable Long id, Principal principal, Model model, RedirectAttributes rttr) {
		Diary diary = service.update_view(id); // ## 수정할 글 가져오기
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
		if (service.update(diary) > 0) {
			msg = "글수정완료!";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/diary/detail/" + diary.getId(); // ## 글수정기능
	}

	@GetMapping("/diary/delete/{id}")
	public String delete_get(@PathVariable Long id, Principal principal, Model model, RedirectAttributes rttr) {
		Diary diary = service.findById(id);
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
<<<<<<< HEAD
		if( service.delete(diary) > 0 ) { msg="글삭제성공!"; }
		rttr.addFlashAttribute("msg",msg);
		service.delete(diary); //## 글삭제기능
		return "redirect:/diary/list"; 
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	@GetMapping("/diary/update/{id}")
	public String update_get(@PathVariable Long id, Principal principal, Model model, RedirectAttributes rttr) {
		Diary diary = service.update_view(id); // ## 수정할 글 가져오기
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
		if (service.update(diary) > 0) {
			msg = "글수정완료!";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/diary/detail/" + diary.getId(); // ## 글수정기능
	}

	@GetMapping("/diary/delete/{id}")
	public String delete_get(@PathVariable Long id, Principal principal, Model model, RedirectAttributes rttr) {
		Diary diary = service.findById(id);
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
=======
>>>>>>> 64f87d4 (0422)
		if (service.delete(diary) > 0) {
			msg = "글삭제성공!";
		}
		rttr.addFlashAttribute("msg", msg);
		service.delete(diary); // ## 글삭제기능
		return "redirect:/diary/list";
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	}
}
