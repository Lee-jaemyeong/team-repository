package com.yoonlee3.diary.goalStatus;

import java.time.LocalDate;
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
	List<GoalStatus> findByGoalId(Long goal_id);
	
	@Query("select count(true) from GoalStatus gs where gs.goal.id= :goal_id and gs.createDate = :currentDate")
	int selectNowGoalSuccess(Long goal_id, LocalDate currentDate);
	//U
	
	@Modifying
	@Transactional
	@Query("update GoalStatus gs set gs.is_success= :is_success where gs.goal.id= :goal_id")
	int updateGoalStatus(Boolean is_success, Long goal_id);

	
	//D
	
}
