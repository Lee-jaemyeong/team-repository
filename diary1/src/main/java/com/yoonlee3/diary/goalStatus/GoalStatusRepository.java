package com.yoonlee3.diary.goalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	
	// 오늘 성공한 목표의 수 구하기
	@Query("select count(true) from GoalStatus gs where gs.goal.id= :goal_id and gs.createDate = :currentDate")
	int findTodaySuccess(Long goal_id, LocalDate currentDate);
	
	@Query("select gs from GoalStatus gs where gs.goal.id = :goal_id and gs.createDate = :today")
	Optional<GoalStatus> findTodayStatus(Long goal_id, LocalDate today);
	
	// 이번 달 성공한 목표의 수 구하기
	@Query("select count(true) from GoalStatus gs where gs.goal.id= :goal_id and "
			+ "gs.createDate >= :startOfMonth and gs.createDate < :startOfNextMonth")
	int findMonthStatus(Long goal_id, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);
	
	// 해당 목표들의 상태들 가져오기
	@Query("select gs from GoalStatus gs where gs.goal.id= :goal_id")
	List<GoalStatus> findStatusByGoalId(Long goal_id);
	
	//U
	
	@Modifying
	@Transactional
	@Query("update GoalStatus gs set gs.is_success= :is_success where gs.goal.id= :goal_id")
	int updateGoalStatus(Boolean is_success, Long goal_id);
	
	
	Optional<GoalStatus> findByGoalAndCreateDate(Goal goal, LocalDate today);

	
	//D
	
}
