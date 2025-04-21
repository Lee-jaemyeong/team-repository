package com.yoonlee3.diary.userAchiv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;

@Service
public class UserAchivService {

	@Autowired
	UserAchivRepository userAchivRepository;
	@Autowired
	UserAchivService userAchivService;
	
	public UserAchiv selectById(Goal goal) {
		return userAchivRepository.selectById(goal.getId());
	}
	
	public int countStatus(Goal goal) {
		
		LocalDate today = LocalDate.now();
		LocalDateTime startOfMonth = LocalDateTime.of(today.withDayOfMonth(1), LocalTime.MIN);
		LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);
		
		return userAchivRepository.selectMonthStatus( goal.getId(), startOfMonth , startOfNextMonth );
	}
	
	public UserAchiv insertUserAchiv(Goal goal) {
		// 현재 달의 마지막 날
		int lastDay = YearMonth.now().lengthOfMonth();
		
		// True가 몇개 인지
		int countTrue = userAchivService.countStatus(goal);
		
		// 달성률 계산
		double userAchivCalc =  countTrue / (double)lastDay;
		
		UserAchiv userAchiv = new UserAchiv();
		userAchiv.setGoal(goal);
		userAchiv.setCompletionRate(userAchivCalc);
		
		return userAchivRepository.save(userAchiv);
	}
}
