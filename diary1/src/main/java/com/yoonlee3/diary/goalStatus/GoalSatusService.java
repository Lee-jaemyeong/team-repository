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

@Service
public class GoalSatusService {

	@Autowired GoalStatusRepository goalStatusRepository;
	@Autowired GoalRepository goalRepository;
	@Autowired UserAchivRepository userAchivRepository;
	
	//C
	public GoalStatus insertGoalStatus(Goal goal, GoalStatus goalStatus) {
		goalStatus.setGoal(goal);
		return goalStatusRepository.save(goalStatus);
	}
	
	//R
	public List<GoalStatus> findGoal(Goal goal) {
		return goalStatusRepository.findByGoalId(goal.getId());
	}
	
	public int countStatus(Goal goal) {
		
		LocalDate today = LocalDate.now();
		LocalDateTime startOfMonth = LocalDateTime.of(today.withDayOfMonth(1), LocalTime.MIN);
		LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);
		
		return userAchivRepository.selectMonthStatus(goal.getId(), startOfMonth, startOfNextMonth);
	}
	
	//U
	public int updateGoal(GoalStatus goalStatus, Goal goal) {
		return goalStatusRepository.updateGoalStatus(goalStatus.getIs_success(), goal.getId());
	}
	
	//D
	
}
