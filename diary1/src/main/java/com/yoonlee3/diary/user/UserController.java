package com.yoonlee3.diary.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.user_kakao.KakaoLogin;

@Controller
public class UserController {

<<<<<<< HEAD
<<<<<<< HEAD
	@Autowired UserService service;
	@Autowired UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired KakaoLogin api;
	
	@GetMapping("/")
	public String main(Model model) { 
		model.addAttribute("url" , api.step1());
		return "user/login"; }
=======
	@Autowired
	UserService service;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String main() {
		return "user/login";
	}
	// localhost:8080/user/login
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	
=======
	@Autowired
	UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	KakaoLogin api;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("url", api.step1());
		return "user/login";
	}

	@GetMapping("/user/login")
	public String login() {
		return "user/login";
	}

>>>>>>> 64f87d4 (0422)
	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
<<<<<<< HEAD
<<<<<<< HEAD
		    User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일에 해당하는 사용자를 찾을 수 없습니다: " + email));
		    model.addAttribute("nickname", user.getUsername());
=======
			User user = userRepository.findByEmail(email).orElseThrow();
			model.addAttribute("nickname", user.getUsername());
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
			User user = userService.findByEmail(email);
			model.addAttribute("nickname", user.getUsername());
>>>>>>> 64f87d4 (0422)
		} else {
			model.addAttribute("nickname", "Guest");
		}
	}

	@GetMapping("/user/mypage")
	public String myPage(Model model, Principal principal) {
		String email = principal.getName();

<<<<<<< HEAD
<<<<<<< HEAD
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다."));
		model.addAttribute("nickname", user.getUsername()); 
=======
		User user = userRepository.findByEmail(email).orElseThrow();
		model.addAttribute("nickname", user.getUsername());
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
		return "user/mypage";
	}

	@GetMapping("/user/login")
<<<<<<< HEAD
	public String login() {  return "user/login"; }
	
=======
	public String login() {
		return "user/login";
	}

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
		User user = userService.findByEmail(email);
		model.addAttribute("nickname", user.getUsername());
		return "user/mypage";
	}

>>>>>>> 64f87d4 (0422)
	@PostMapping("/user/login")
	public String login_form() {
		return "user/login";
	}

	@GetMapping("/user/join")
	public String join(UserForm userForm) {
		return "user/join";
	}

	@PostMapping("/user/join")
	public String join(@Valid UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "user/join";
		}
		if (!userForm.getPassword().equals(userForm.getPassword2())) {
			bindingResult.rejectValue("password2", "pawordInCorrect", "패스워드를 확인해주세요");
			return "user/join";
		}

		try {
			User user = new User();
			user.setUsername(userForm.getUsername());
			user.setEmail(userForm.getEmail());
			user.setPassword(userForm.getPassword());
<<<<<<< HEAD
			service.insertUser(user);
=======
			userService.insertUser(user);
>>>>>>> 64f87d4 (0422)
		} catch (DataIntegrityViolationException e) { // 무결성 - 중복키, 외래키제약, 데이터형식불일치
			e.printStackTrace();
			bindingResult.reject("failed", "등록된 유저입니다.");
			return "user/join";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("failed", e.getMessage());
			return "user/join";
		}
		return "user/login";
	}

	@GetMapping("/user/find")
	public String find() {
		return "user/find";
	}

	@PostMapping("/user/find")
	public String find_form(@RequestParam("email") String email, Model model) {
<<<<<<< HEAD
		Optional<User> user = userRepository.findByEmail(email);
=======
		User user = userService.findByEmail(email);
>>>>>>> 64f87d4 (0422)

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentEmail;

		if (authentication.getPrincipal() instanceof UserDetails) {
			currentEmail = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentEmail = authentication.getPrincipal().toString();
		}

<<<<<<< HEAD
		if (user.isPresent() && user.get().getEmail().equals(currentEmail)) {
=======
		if ( user.getEmail().equals(currentEmail)) {
>>>>>>> 64f87d4 (0422)
			model.addAttribute("msg", "비밀번호 재설정 페이지로 이동합니다.");
			return "user/passchange";
		} else {
			model.addAttribute("msg", "이메일을 확인해주세요.");
			return "user/find";
		}
	}
<<<<<<< HEAD

	@GetMapping("/user/passchange")
	public String passchange() {
		return "user/passchange";
	}

	@PostMapping("/user/passchange")
	public String passchange_form(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedEmail = authentication.getName();
		Optional<User> opuser = userRepository.findByEmail(email);

		if (opuser.isPresent()) {
			User user = opuser.get();

			if (loggedEmail.equals(user.getEmail())) {
				String encodedPassword = passwordEncoder.encode(password);
				user.setPassword(encodedPassword);
				userRepository.save(user);

				model.addAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
				return "redirect:/user/mypage";
			} else {
				model.addAttribute("msg", "다시 입력해주세요.");
				return "user/passchange";
			}
		} else {
			model.addAttribute("msg", "다시 입력해주세요.");
			return "user/passchange";
		}
	}
<<<<<<< HEAD
	
	@GetMapping("/fragments/sidebar/nickname")
	public String userChange() { return "fragments/sideBarMypage"; }
	
	@PostMapping("/fragments/sidebar/nickname")
	public String userChange_form(@RequestParam String username,
	                              Principal principal,
	                              RedirectAttributes redirectAttributes) {

	    String email = principal.getName();
	    Optional<User> opUser = userRepository.findByEmail(email);

	    if (opUser.isPresent()) {
	        User user = opUser.get();

	        user.setUsername(username);
	        userRepository.save(user);

	        redirectAttributes.addFlashAttribute("msg", "닉네임이 변경되었습니다.");
	        return "redirect:/user/mypage";
	    } else {
	        redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
	        return "redirect:/fragments/sideBarMypage";
	    }
	}
	
	@GetMapping("/fragments/sidebar/delete")
	public String userdelete() { return "fragments/sideBarMypage"; }
	
	@PostMapping("/fragments/sidebar/delete")
	public String userdelete_form(@RequestParam("password") String password,
	                              Principal principal,
	                              RedirectAttributes redirectAttributes) {
		
=======

	@GetMapping("/user/userchange")
	public String userChange() {
		return "user/userchange";
	}

	@PostMapping("/user/userchange")
	public String userChange_form(@RequestParam String username, Principal principal,
			RedirectAttributes redirectAttributes) {

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
		String email = principal.getName();
		Optional<User> opUser = userRepository.findByEmail(email);

		if (opUser.isPresent()) {
			User user = opUser.get();

<<<<<<< HEAD
	            redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 완료되었습니다.");
	            return "redirect:/user/login";
	        } else {
	            redirectAttributes.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
	            return "redirect:/fragments/sideBarMypage"; }
	    } else {
	        redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
	        return "redirect:/fragments/sideBarMypage"; }
=======
			user.setUsername(username);
			userRepository.save(user);
=======

	@GetMapping("/user/passchange")
	public String passchange() {
		return "user/passchange";
	}

	@PostMapping("/user/passchange")
	public String passchange_form(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedEmail = authentication.getName();
		User user = userService.findByEmail(email);

		if ( user != null) {

			if (loggedEmail.equals(user.getEmail())) {
				String encodedPassword = passwordEncoder.encode(password);
				user.setPassword(encodedPassword);
				userService.insertUser(user);

				model.addAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
				return "redirect:/user/mypage";
			} else {
				model.addAttribute("msg", "다시 입력해주세요.");
				return "user/passchange";
			}
		} else {
			model.addAttribute("msg", "다시 입력해주세요.");
			return "user/passchange";
		}
	}

	@GetMapping("/fragments/sidebar/nickname")
	public String userChange() {
		return "fragments/sideBarMypage";
	}

	@PostMapping("/fragments/sidebar/nickname")
	public String userChange_form(@RequestParam String username, Principal principal,
			RedirectAttributes redirectAttributes) {

		String email = principal.getName();
		User user = userService.findByEmail(email);

		if (user != null) {

			user.setUsername(username);
			userService.insertUser(user);
>>>>>>> 64f87d4 (0422)

			redirectAttributes.addFlashAttribute("msg", "닉네임이 변경되었습니다.");
			return "redirect:/user/mypage";
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
<<<<<<< HEAD
			return "redirect:/user/userchange";
		}
	}

	@GetMapping("/user/userdelete")
	public String userdelete() {
		return "user/userdelete";
	}

	@PostMapping("/user/userdelete")
=======
			return "redirect:/fragments/sideBarMypage";
		}
	}

	@GetMapping("/fragments/sidebar/delete")
	public String userdelete() {
		return "fragments/sideBarMypage";
	}

	@PostMapping("/fragments/sidebar/delete")
>>>>>>> 64f87d4 (0422)
	public String userdelete_form(@RequestParam("password") String password, Principal principal,
			RedirectAttributes redirectAttributes) {

		String email = principal.getName();
<<<<<<< HEAD
		Optional<User> opUser = userRepository.findByEmail(email);

		if (opUser.isPresent()) {
			User user = opUser.get();

			if (passwordEncoder.matches(password, user.getPassword())) {
				userRepository.delete(user);
=======
		User user = userService.findByEmail(email);

		if ( user != null) {

			if (passwordEncoder.matches( password, user.getPassword())) {
				userService.deleteByEmailAndPassword(password, email);
>>>>>>> 64f87d4 (0422)
				SecurityContextHolder.clearContext();

				redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 완료되었습니다.");
				return "redirect:/user/login";
			} else {
				redirectAttributes.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
<<<<<<< HEAD
				return "redirect:/user/userdelete";
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
			return "redirect:/user/userdelete";
		}
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
				return "redirect:/fragments/sideBarMypage";
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
			return "redirect:/fragments/sideBarMypage";
		}
>>>>>>> 64f87d4 (0422)
	}
}
