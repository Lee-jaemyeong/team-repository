package com.yoonlee3.diary.goalStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalRepository;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.user.User;

@Service
public class GoalSatusService {

	@Autowired GoalStatusRepository goalStatusRepository;
	@Autowired GoalRepository goalRepository;
	
	//C
	public GoalStatus insertGoalStatus(Goal goal, GoalStatus goalStatus) {
		goalStatus.setGoal_id(goal);
		return goalStatusRepository.save(goalStatus);
	}
	
	//R
	public List<GoalStatus> selectGoal(Goal goal) {
		return goalStatusRepository.selectByGoalId(goal.getGoal_id());
	}
	
	//U
	public int updateGoal(GoalStatus goalStatus, Goal goal) {
		return goalStatusRepository.updateGoalStatus(goalStatus.getIs_success(), goal.getGoal_id());
	}
	
	//D
	
}
