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
<<<<<<< HEAD
	
	//C
=======

	// C
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	public Goal insertGoal(Goal goal, User user) {
		goal.setUser(user);
		return goalRepository.save(goal);
	}
<<<<<<< HEAD
	
	//R
	public Goal findByGaolId(Goal goal) {
		return goalRepository.findByGoalId(goal.getId());
	}
	
	public List<Goal> findByUserId(User user){
		return goalRepository.findByUserId(user.getId());
	}
	
	//U
	public int updateGoal(Goal goal, OpenScope openscope ) {
		return goalRepository.updateByGoalId(goal.getDueDate(), goal.getGoal_content(), openscope.getId(),
				goal.getId());
		//int updateByGoalId(Date due_date, String goal_content, Long open_scope_id, Long goal_id);
=======

	// R
	public Goal selectByGaolId(Goal goal) {
		return goalRepository.selectByGoalId(goal.getId());
	}

	public List<Goal> selectByUserId(User user) {
		return goalRepository.selectByUserId(user.getId());
	}

	// U
	public int updateGoal(Goal goal, OpenScope openscope) {
		return goalRepository.updateByGoalId(goal.getDueDate(), goal.getGoal_content(), openscope.getId(),
				goal.getId());
		// int updateByGoalId(Date due_date, String goal_content, Long open_scope_id,
		// Long goal_id);
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}

	// D
	public int deleteGoal(Goal goal, User user) {
		return goalRepository.deleteGoal(goal.getId(), user.getId());
	}

}
