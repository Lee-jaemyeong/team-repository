package com.yoonlee3.diary.user;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.diary.Diary;
import com.yoonlee3.diary.diary.DiaryService;
import com.yoonlee3.diary.follow.Follow;
import com.yoonlee3.diary.follow.FollowRepository;
import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalService;
import com.yoonlee3.diary.goalStatus.GoalSatusService;
import com.yoonlee3.diary.goalStatus.GoalStatus;
import com.yoonlee3.diary.goalStatus.GoalStatusRepository;
import com.yoonlee3.diary.group.GroupService;
import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.user_kakao.KakaoLogin;

@Controller
public class UserController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	KakaoLogin api;
	@Autowired
	private GoalService goalService;
	@Autowired
	JoinToGroupService joinToGroupService;
	@Autowired
	DiaryService diaryService;
	@Autowired
	GroupService groupService;
	@Autowired
	GoalSatusService goalSatusService;
	@Autowired
	GoalStatusRepository goalStatusRepository;
	@Autowired
	FollowRepository followRepository;

	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			if (user != null) {
				model.addAttribute("nickname", user.getUsername());
				model.addAttribute("user", user);
				Set<YL3Group> groups = joinToGroupService.findGroupById(user.getId());
				model.addAttribute("groups", groups);
			} else {
				model.addAttribute("nickname", "Guest"); // 사용자 없음 -> Guest로 처리
				model.addAttribute("groups", Collections.emptySet());
			}
		} else {
			model.addAttribute("nickname", "Guest"); // 로그인되지 않으면 Guest로 처리
		}
	}

	// 처음 화면
	@GetMapping("/")
	public String main(Model model) {
		em.clear();
		model.addAttribute("url", api.step1());
		return "user/login";
	}

	// 마이페이지
	@GetMapping("/user/mypage")
	public String myPage(
			@RequestParam(value = "selectedDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			Model model, Principal principal) {
		model.addAttribute("isMyPage", true);
		String email = principal.getName();

		User user = userService.findByEmail(email);

		// 날짜 계산해서 오늘 할 목표 넘기기
		LocalDate selectedDate = (date != null) ? date : LocalDate.now();
		List<Goal> goals = goalService.findTodayGoalByUserId(user, selectedDate);

		Map<Long, GoalStatus> goalStatusMap = new HashMap<>();
		for (Goal goal : goals) {
			goalSatusService.findTodayGoalStatus(goal, selectedDate)
					.ifPresent(status -> goalStatusMap.put(goal.getId(), status));
		}
		model.addAttribute("goalStatusMap", goalStatusMap);

		model.addAttribute("selectedDate", selectedDate);
		System.out.println(".......goal list........" + goals);
		model.addAttribute("goal", goals);

		// 해당 사용자가 쓴 글만 가져오기
		List<Diary> list = diaryService.findByUserId(user.getId());
		model.addAttribute("list", list);

		return "user/mypage";
	}

	@GetMapping("/mypage")
	public String goMypage() {
		return "user/mypage";
	}

	// 로그인
	@GetMapping("/user/login")
	public String login(Model model) {
		model.addAttribute("url", api.step1());
		return "user/login";
	}

	@PostMapping("/user/login")
	public String login_form(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) {
		// 이메일로 유저를 찾기
		Optional<User> opUser = userRepository.findByEmail(email);

		if (opUser.isPresent()) {
			User user = opUser.get();

			// 입력된 비밀번호와 저장된 암호화된 비밀번호 비교
			if (passwordEncoder.matches(password, user.getPassword())) {
				// 비밀번호가 맞다면 로그인 성공
				return "redirect:/mypage"; // 또는 메인 페이지로 리디렉션
			} else {
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "user/login"; // 비밀번호 불일치시 로그인 페이지로
			}
		} else {
			model.addAttribute("msg", "이메일을 찾을 수 없습니다.");
			return "user/login"; // 이메일을 찾을 수 없을 경우 로그인 페이지로
		}
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
		try {
			User user = userService.findByEmail(email);

			model.addAttribute("msg", "비밀번호 재설정 페이지로 이동합니다.");
			return "user/passchange";

		} catch (RuntimeException e) {
			model.addAttribute("msg", "이메일을 입력해주세요.");
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

		if (user != null) {
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

	// 사이드 바 닉네임 변경
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

			if (userService.SameUsername(username)) {
				redirectAttributes.addFlashAttribute("msg", "이미 존재하는 닉네임입니다.");
				return "redirect:/mypage";
			}

			user.setUsername(username);
			userService.insertUser(user);

			redirectAttributes.addFlashAttribute("msg", "닉네임이 변경되었습니다.");
			return "redirect:/mypage";
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
			return "redirect:/mypage";
		}
	}

	// 사이드바 탈퇴하기
	@GetMapping("/fragments/sidebar/delete")
	public String userdelete() {
		return "fragments/sideBarMypage";
	}

	@PostMapping("/user/delete")
	public String userdelete_form(@RequestParam("password") String password, Principal principal,
			RedirectAttributes redirectAttributes) {

		String email = principal.getName();
		Optional<User> opUser = userRepository.findByEmail(email);

		if (opUser.isPresent()) {
			User user = opUser.get();

			if (passwordEncoder.matches(password, user.getPassword())) {
				userRepository.delete(user);
				SecurityContextHolder.clearContext();

				redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 완료되었습니다.");
				return "redirect:/user/login";
			} else {
				redirectAttributes.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "redirect:/mypage";
			}
		} else {
			redirectAttributes.addFlashAttribute("msg", "사용자 정보를 찾을 수 없습니다.");
			return "redirect:/mypage";
		}
	}

	// 메인
	@GetMapping("/main")
	public String goMain(Model model) {
		model.addAttribute("isMainPage", true);
		return "mainTemplate/main";
	}

	// 팔로우 화면
	@GetMapping("/user/follow")
	public String showFollowPage(@RequestParam(value = "userId", required = false) Long userId, Model model,
			Principal principal, HttpServletRequest request) {

		if (principal == null) {
			return "redirect:/user/login"; // 로그인 안 한 경우
		}

		User currentUser = userService.findByEmail(principal.getName());
		Long currentUserId = currentUser.getId();
		model.addAttribute("currentUserId", currentUserId);

		if (userId == null || userId.equals(currentUserId)) {
			userId = currentUserId;
		}

		Optional<User> targetUserOpt = userRepository.findById(userId);
		if (targetUserOpt.isEmpty())
			return "error";

		User targetUser = targetUserOpt.get();

		Set<Long> followingIds = new HashSet<>();
		List<Follow> userFollowings = followRepository.findByFollower(currentUser);
		for (Follow f : userFollowings) {
			followingIds.add(f.getFollowing().getId());
		}

		List<Follow> followers = followRepository.findByFollowing(targetUser);
		List<Follow> followings = followRepository.findByFollower(targetUser);

		model.addAttribute("followers", followers != null ? followers : new ArrayList<>());
		model.addAttribute("followings", followings != null ? followings : new ArrayList<>());
		model.addAttribute("targetUserId", targetUser.getId());
		model.addAttribute("followingIds", followingIds != null ? followingIds : new HashSet<>());

		// CSRF 토큰 추가
		CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("_csrf", csrfToken);

		return "user/follow";
	}

	// 유저 찾기
	@GetMapping("/search/users")
	@ResponseBody
	public List<UserProfileDto> searchUsers(@RequestParam String keyword) {
		List<User> users = userService.searchUsers(keyword);

		// 로그로 users 확인
		System.out.println("검색된 유저들: " + users);

		return users.stream().map(user -> new UserProfileDto(user.getId(), user.getUsername()))
				.collect(Collectors.toList());
	}

}
