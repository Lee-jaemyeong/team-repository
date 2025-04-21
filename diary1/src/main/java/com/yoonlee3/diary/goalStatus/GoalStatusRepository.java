package com.yoonlee3.diary.goalStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yoonlee3.diary.goal.Goal;

public interface GoalStatusRepository extends JpaRepository<GoalStatus, Long>{
	
	//C
	//R
	@Query("select gs from GoalStatus gs where gs.goal.id=:goal_id")
	List<GoalStatus> selectByGoalId(Long goal_id);
	
	//U
	
	@Modifying
	@Transactional
	@Query("update GoalStatus gs set gs.is_success= :is_success where gs.goal.id= :goal_id")
	int updateGoalStatus(Boolean is_success, Long goal_id);

	
	//D
	
}
