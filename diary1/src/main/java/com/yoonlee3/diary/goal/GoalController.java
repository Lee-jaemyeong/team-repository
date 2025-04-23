package com.yoonlee3.diary.goal;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yoonlee3.diary.goalStatus.GoalSatusService;
import com.yoonlee3.diary.goalStatus.GoalStatus;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.openScope.OpenScopeService;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

@Controller
public class GoalController {

	@Autowired
	GoalService goalService;
	@Autowired
	UserService userService;
	@Autowired
	GoalSatusService goalSatusService;
	@Autowired
	OpenScopeService openScopeService;

	// 목표 생성하기

	@PostMapping("goal/insert")
	public String goalInsert_post(Principal principal, Goal goal) {
		String email = principal.getName();
		User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
		goalService.insertGoal(goal, user);
		return "redirect:/user/mypage";
	}

	// 목표 수정하기
	@GetMapping("goal/update/{id}")
	public String goalUpdate_get(@PathVariable("id") Long goal_id, Model model) {
		Goal findGoal = goalService.findByGoalId(goal_id);
		model.addAttribute("findGoal", findGoal);
		return "user/mypage";
	}

	// 목표 수정하기
	@PostMapping("goal/update/{id}")
	public String goalUpdate_post(@PathVariable("id") Long goal_id, @RequestParam String goal_content,
			@RequestParam Date dueDate, @RequestParam Long open_scope_id) {
		Goal goal = goalService.findByGoalId(goal_id);
		OpenScope openScope = openScopeService.findOpenScopeId(open_scope_id);

		goal.setDueDate(dueDate);
		goal.setGoal_content(goal_content);

		goal.setOpenScope(openScope);
		goalService.updateGoal(goal);
		return "redirect:/user/mypage";
	}

	// 목표 성공
	@PostMapping("goal/success/{id}")
	public String goalIsSuccess_post(@PathVariable("id") Long goal_id, @RequestParam( value="is_success", required = false) Boolean is_success) {
		
		System.out.println("컨트롤러로 잘 넘어왔니............? :" + is_success);

		//오늘 날짜 확인
		LocalDate today = LocalDate.now();
		
		Goal goal = goalService.findByGoalId(goal_id);
		// 기존 상태가 존재하는지 확인
		Optional<GoalStatus> findGoalStatus = goalSatusService.findByGoalAndCreateDate(goal, today);

		GoalStatus goalStatus;

		if (findGoalStatus.isPresent()) {
			goalStatus = findGoalStatus.get();
			System.out.println("..................체크 성공.........?"+is_success);
			// 체크박스가 비어있으면 false 처리
			goalStatus.setIs_success(is_success != null ? is_success : false);
		} else {
			goalStatus = new GoalStatus();
			goalStatus.setGoal(goal);
			goalStatus.setCreateDate(today);
			System.out.println("..................체크 성공.........?"+is_success);
			goalStatus.setIs_success(is_success != null ? is_success : false); // 체크박스가 비어있으면 false 처리
		}

		goalSatusService.insertGoalStatus(goalStatus); // 상태 저장

		return "redirect:/user/mypage"; // 변경된 목표 상태 반영 후 페이지로 리디렉션
	}

	// 목표 삭제하기
	@PostMapping("goal/delete/{id}")
	public String goalDelete_post(@PathVariable("id") Long goal_id, Principal principal) {

		String email = principal.getName();
		User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
		Goal goal = goalService.findByGoalId(goal_id);

		goalService.deleteGoal(goal, user.getId());
		return "redirect:/user/mypage";
	}

}
