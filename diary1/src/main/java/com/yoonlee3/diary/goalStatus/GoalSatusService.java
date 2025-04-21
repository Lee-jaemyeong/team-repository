package com.yoonlee3.diary.goalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalRepository;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.userAchiv.UserAchivRepository;
import com.yoonlee3.diary.userAchiv.UserAchivService;

@Service
public class GoalSatusService {

	@Autowired
	GoalStatusRepository goalStatusRepository;
	@Autowired
	GoalRepository goalRepository;
	@Autowired
	UserAchivRepository userAchivRepository;

	// C
	public GoalStatus insertGoalStatus(Goal goal, GoalStatus goalStatus) {
		goalStatus.setGoal(goal);
		return goalStatusRepository.save(goalStatus);
	}

	// R : 유저의 목표 리스트 가져오기
	public List<GoalStatus> selectGoal(Goal goal) {
		return goalStatusRepository.selectByGoalId(goal.getId());
	}
	
	// 현재 달의 목표 상태들 가져오기
	public int countStatus(Goal goal) {
		
		LocalDate today = LocalDate.now();
		LocalDateTime startOfMonth = LocalDateTime.of(today.withDayOfMonth(1), LocalTime.MIN);
		LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);
		
		return userAchivRepository.selectMonthStatus(goal.getId(), startOfMonth, startOfNextMonth);
	}
	
	// U
	public int updateGoal(GoalStatus goalStatus, Goal goal) {
		return goalStatusRepository.updateGoalStatus(goalStatus.getIs_success(), goal.getId());
	}
	

	// D

}
