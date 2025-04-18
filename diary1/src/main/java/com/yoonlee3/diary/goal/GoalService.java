package com.yoonlee3.diary.goal;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.user.User;

@Service
public class GoalService {

	@Autowired
	GoalRepository goalRepository;
	
	//C
	public Goal insertGoal(Goal goal) {
		return goalRepository.save(goal);
	}
	
	//R
	public Goal selectByGaolId(Goal goal) {
		return goalRepository.selectByGoalId(goal.getGoal_id());
	}
	
	public List<Goal> selectByUserId(User user){
		return goalRepository.selectByUserId(user.getUser_id());
	}
	
	//U
	public int updateGoal(Goal goal, OpenScope openscope ) {
		return goalRepository.updateByGoalId(goal.getDue_date(), goal.getGoal_content(), openscope.getOpen_scope_id(), goal.getGoal_id());
		//int updateByGoalId(Date due_date, String goal_content, Long open_scope_id, Long goal_id);
	}
	
	//D
	public int deleteGoal(Goal goal, User user) {
		return goalRepository.deleteGoal(goal.getGoal_id(), user.getUser_id());
	}
	
}
