package com.yoonlee3.diary.user;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalService;
import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.user_kakao.KakaoLogin;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	KakaoLogin api;
	@Autowired
	private GoalService goalService;
	@Autowired
	JoinToGroupService joinToGroupService;
	
	// 처음 화면
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("url", api.step1());
		return "user/login";
	}
	
	// 로그인 된 유저 닉네임 설정
	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			model.addAttribute("nickname", user.getUsername());
		} else {
			model.addAttribute("nickname", "Guest");
		}
	}

	@GetMapping("/user/login")
	public String login(Model model) {
		model.addAttribute("url" , api.step1());
		return "user/login";
	}
	
	// 마이페이지
	@GetMapping("/user/mypage")
	public String myPage(Model model, Principal principal) {
		model.addAttribute("isMyPage", true);
		String email = principal.getName();

		User user = userService.findByEmail(email);
		System.out.println(user.getNickname());
		model.addAttribute("nickname", user.getUsername());
		
		List<Goal> goal = goalService.findTodayGoalByUserId(user);
		System.out.println(".......goal list........"+goal);
		model.addAttribute("goal", goal);
		
		Set<YL3Group> groups = joinToGroupService.findGroupById(user.getId());
		System.out.println("그룹 잘 가져왔니............?" + groups.toString());
		model.addAttribute("groups", groups);
		
		return "user/mypage";
	}

	@PostMapping("/user/login")
	public String login_form() {
		return "user/login";
	}
	
	// 가입하기 화면
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
			userService.insertUser(user);
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
	
	// 유저 찾기
	@GetMapping("/user/find")
	public String find() {
		return "user/find";
	}

	@PostMapping("/user/find")
	public String find_form(@RequestParam("email") String email, Model model) {
		User user = userService.findByEmail(email);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentEmail;

		if (authentication.getPrincipal() instanceof UserDetails) {
			currentEmail = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			currentEmail = authentication.getPrincipal().toString();
		}

		if ( user.getEmail().equals(currentEmail)) {
			model.addAttribute("msg", "비밀번호 재설정 페이지로 이동합니다.");
			return "user/passchange";
		} else {
			model.addAttribute("msg", "이메일을 확인해주세요.");
			return "user/find";
		}
	}
	
	// 비밀번호
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

			redirectAttributes.addFlashAttribute("msg", "닉네임이 변경되었습니다.");
			return "redirect:/user/mypage";
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
			return "redirect:/fragments/sideBarMypage";
		}
	}

	@GetMapping("/fragments/sidebar/delete")
	public String userdelete() {
		return "fragments/sideBarMypage";
	}

	@PostMapping("/fragments/sidebar/delete")
	public String userdelete_form(@RequestParam("password") String password, Principal principal,
			RedirectAttributes redirectAttributes) {

		String email = principal.getName();
		User user = userService.findByEmail(email);

		if ( user != null) {

			if (passwordEncoder.matches( password, user.getPassword())) {
				userService.deleteByEmailAndPassword(password, email);
				SecurityContextHolder.clearContext();

				redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 완료되었습니다.");
				return "redirect:/user/login";
			} else {
				redirectAttributes.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "redirect:/fragments/sideBarMypage";
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
			return "redirect:/fragments/sideBarMypage";
		}
	}
	
	@GetMapping("/mypage")
	public String goMypage() {
	    return "user/mypage";
	}
	
	@GetMapping("/main")
	public String goMain(Model model) {
		model.addAttribute("isMainPage", true);
		return "mainTemplate/main";
	}
	@GetMapping("/user/follow")
	public String follow() {return "user/follow";}	
}
