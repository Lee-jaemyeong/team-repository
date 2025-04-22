package com.yoonlee3.diary.userAchiv;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goalStatus.GoalSatusService;

@Service
public class UserAchivService {

	@Autowired
	UserAchivRepository userAchivRepository;
	@Autowired
	GoalSatusService goalSatusService;

<<<<<<< HEAD
	public Optional<UserAchiv> selectById(Goal goal) {
		return userAchivRepository.findById(goal.getId());
=======
	public UserAchiv selectById(Goal goal) {
		return userAchivRepository.selectById(goal.getId());
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}


	public UserAchiv insertUserAchiv(Goal goal) {
		// 현재 달의 마지막 날
		int lastDay = YearMonth.now().lengthOfMonth();

		// True가 몇개 인지
<<<<<<< HEAD
		int countTrue = goalSatusService.countStatus(goal);

		// 달성률 계산
		double userAchivCalc = countTrue / (double) lastDay;
=======
		int countSuccess = goalSatusService.countStatus(goal);

		// 달성률 계산
		double userAchivCalc = countSuccess / (double) lastDay;
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1

		UserAchiv userAchiv = new UserAchiv();
		userAchiv.setGoal(goal);
		userAchiv.setCompletionRate(userAchivCalc);

		return userAchivRepository.save(userAchiv);
	}
}
