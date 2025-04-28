package com.yoonlee3.diary.goal;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yoonlee3.diary.Diary1Application;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.openScope.OpenScopeRepository;
import com.yoonlee3.diary.openScope.OpenScopeService;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;
import com.yoonlee3.diary.user.UserService;

@SpringBootTest(classes = Diary1Application.class)
public class GoalTest {
	
	@Autowired
	GoalService goalService;
	@Autowired
	OpenScopeRepository openScopeRepository;
	@Autowired
	UserService userService;
	
	@Test
	void insert() {
		Goal goal = new Goal();
		User user = userService.findById(1l);
		
		goal.setGoal_content("매일 30분 독서하기");
		

		LocalDate dueDate = LocalDate.parse("2025-04-01");
		goal.setDueDate(dueDate);
		
		OpenScope openscope = openScopeRepository.findById(4L).orElseThrow(()-> new RuntimeException("존재하지 않는 공개범위입니다."));
		goal.setOpenScope(openscope);
		
		goalService.insertGoal(goal, user);
	}
	
}
