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
	@Query("select g from Goal g where g.id = :goal_id")
	Goal findByGoalId(Long goal_id);
	
	@Query("select g from Goal g where g.user.id = :user_id")
	List<Goal> findByUserId(Long user_id);
	
	//U
	@Modifying
	@Transactional
	@Query("update Goal g set g.dueDate= :due_date, g.goal_content= :goal_content, "
			+ "g.openScope.id = :open_scope_id where g.id = :goal_id")
	int updateByGoalId(Date due_date, String goal_content, Long open_scope_id, Long goal_id);
	
	//D
	@Modifying
	@Transactional
	@Query("delete from Goal g where g.id=:goal_id and g.user.id=:user_id")
	int deleteGoal(Long goal_id, Long user_id);
}
