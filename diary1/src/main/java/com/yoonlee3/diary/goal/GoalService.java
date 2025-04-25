package com.yoonlee3.diary.goal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goalStatus.GoalSatusService;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.user.User;

@Service
public class GoalService {

	@Autowired GoalRepository goalRepository;
	@Autowired GoalSatusService goalSatusService;
	
	//C
	public Goal insertGoal(Goal goal, User user) {
		goal.setUser(user);
		return goalRepository.save(goal);
	}
	
	//R
	public Goal findByGoalId(Long goal_id) {
		return goalRepository.findByGoalId(goal_id);
	}
	
	public List<Goal> findByUserId(User user){
		return goalRepository.findByUserId(user.getId());
	}
	
	public List<Goal> findTodayGoalByUserId(User user){
		LocalDate today = LocalDate.now();
		Date toDay = Date.valueOf(today);
		return goalRepository.findTodayGoalByUserId( user.getId() , toDay );
	}
	
	//U
	public int updateGoal(Goal goal) {
		return goalRepository.updateByGoalId(goal.getDueDate(), goal.getGoal_content(), goal.getOpenScope().getId(),
				goal.getId());
		//int updateByGoalId(Date due_date, String goal_content, Long open_scope_id, Long goal_id);
	}
	
	//D
	public int deleteGoal(Goal goal, Long user_id) {
		goalSatusService.deleteStatusByGoal(goal);
		return goalRepository.deleteGoal(goal.getId(), user_id );
	}
	
}
