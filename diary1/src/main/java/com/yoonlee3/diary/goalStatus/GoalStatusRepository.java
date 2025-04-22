package com.yoonlee3.diary.goalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yoonlee3.diary.goal.Goal;

<<<<<<< HEAD
public interface GoalStatusRepository extends JpaRepository<GoalStatus, Long>{
	
	//C
	//R
	@Query("select gs from GoalStatus gs where gs.goal.id=:goal_id")
	List<GoalStatus> findByGoalId(Long goal_id);
=======
public interface GoalStatusRepository extends JpaRepository<GoalStatus, Long> {

	// C

	// R
	@Query("select gs from GoalStatus gs where gs.goal.id=:goal_id")
	List<GoalStatus> selectByGoalId(Long goal_id);
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	
	// 오늘 성공한 목표의 수 구하기
	@Query("select count(true) from GoalStatus gs where gs.goal.id= :goal_id and gs.createDate = :currentDate")
	int findTodaySuccess(Long goal_id, LocalDate currentDate);
<<<<<<< HEAD
	
	// 이번 달 성공한 목표의 수 구하기
	@Query("select count(true) from GoalStatus gs where gs.goal.id= :goal_id and "
			+ "gs.createDate >= :startOfMonth and gs.createDate < :startOfNextMonth")
	int findMonthStatus(Long goal_id, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);
	//U
=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	
	// 이번 달 성공한 목표의 수 구하기
	@Query("select count(true) from GoalStatus gs where gs.goal.id= :goal_id and "
			+ "gs.createDate >= :startOfMonth and gs.createDate < :startOfNextMonth")
	int selectMonthStatus(Long goal_id, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);

	// U
	@Modifying
	@Transactional
	@Query("update GoalStatus gs set gs.is_success= :is_success where gs.goal.id= :goal_id")
	int updateGoalStatus(Boolean is_success, Long goal_id);

	// D

}
