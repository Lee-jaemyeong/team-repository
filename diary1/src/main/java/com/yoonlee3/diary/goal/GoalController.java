package com.yoonlee3.diary.goal;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

@Controller
public class GoalController {

	@Autowired
	GoalService goalService;
	@Autowired
	UserService userService;
	
	@PostMapping("/goal/insert")
	public String goalInsert(Principal principal, Goal goal) {
		String email = principal.getName();
	    User user = userService.findByEmail(email);
	    goalService.insertGoal(goal, user);
		return "user/mypage";
	}
	
	@GetMapping("/user/goalComplate")
	public String goalComplate() {return "/user/goalComplate";}
	
}
