package com.yoonlee3.diary.goalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalRepository;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.userAchiv.UserAchivRepository;

@Service
public class GoalSatusService {

	@Autowired GoalStatusRepository goalStatusRepository;
	@Autowired GoalRepository goalRepository;
	
	//C
	public GoalStatus insertGoalStatus( GoalStatus goalStatus ) {
		
		Goal goal = goalStatus.getGoal();
	    Boolean is_success = goalStatus.getIs_success();

		
		LocalDate today = LocalDate.now();

	    GoalStatus existing = goalStatusRepository.findByGoalAndCreateDate(goal, today)
	        .orElse(null);

	    if (existing != null) {
	        existing.setIs_success(is_success);
	        return goalStatusRepository.save(existing);
	    } else {
	        GoalStatus newStatus = new GoalStatus();
	        newStatus.setGoal(goal);
	        newStatus.setCreateDate(today);
	        newStatus.setIs_success(is_success);
	        return goalStatusRepository.save(newStatus);
	    }
	
	}
	
	// R : 유저의 목표 리스트 가져오기
	public List<GoalStatus> selectGoal(Goal goal) {
		return goalStatusRepository.findByGoalId(goal.getId());
	}
	
	// 해당목표의 상태들 가져오기
	public List<GoalStatus> findByGoalId(Goal goal){
		return goalStatusRepository.findStatusByGoalId(goal.getId());
	}
	
	
	// 오늘 성공한 목표의 수 구하기
	public int findTodaySuccess(Goal goal) {
		LocalDate currentDate = LocalDate.now();
		return goalStatusRepository.findTodaySuccess(goal.getId(), currentDate );
	}
	
	// 현재 달의 목표 상태들 가져오기
	public int countStatus(Goal goal) {
		
		LocalDate today = LocalDate.now();
		LocalDateTime startOfMonth = LocalDateTime.of(today.withDayOfMonth(1), LocalTime.MIN);
		LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);
		
		return goalStatusRepository.findMonthStatus(goal.getId(), startOfMonth, startOfNextMonth);
	}
	
	public Optional<GoalStatus> findStatusById(Goal goal) {
		return goalStatusRepository.findById(goal.getId());
	}
	
	public Optional<GoalStatus> findByGoalAndCreateDate(Goal goal, LocalDate today) {
		return goalStatusRepository.findByGoalAndCreateDate(goal, today);
	}
	
	//U
	public int updateGoal(GoalStatus goalStatus, Goal goal) {
		return goalStatusRepository.updateGoalStatus(goalStatus.getIs_success(), goal.getId());
	}

	
	//D
	public void deleteStatusByGoal(Goal goal) {
		List<GoalStatus> goalStatuses = goalStatusRepository.findByGoalId(goal.getId());
		goalStatusRepository.deleteAll(goalStatuses);
	}
	
}
