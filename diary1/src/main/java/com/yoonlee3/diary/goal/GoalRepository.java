package com.yoonlee3.diary.goal;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
	
	//C
	
	//R
	@Query("select goal from Goal goal where goal.goal_id = :goal_id")
	Goal selectByGoalId(Long goal_id);
	
	@Query("select goal from Goal goal where goal.user_id = :user_id")
	List<Goal> selectByUserId(Long user_id);
	
	//U
	@Modifying
	@Transactional
	@Query("update Goal goal set goal.due_date= :due_date, goal.goal_content= :goal_content, "
			+ "goal.open_scope_id = :open_scope_id where goal.goal_id = :goal_id")
	int updateByGoalId(Date due_date, String goal_content, Long open_scope_id, Long goal_id);
	
	//D
	@Modifying
	@Transactional
	@Query("delete from Goal goal where goal.goal_id=:goal_id and goal.user_id=:user_id")
	int deleteGoal(Long goal_id, Long user_id);
}
