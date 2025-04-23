package com.yoonlee3.diary.groupAchiv;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalService;
import com.yoonlee3.diary.goalStatus.GoalSatusService;
import com.yoonlee3.diary.group.GroupService;
import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistoryService;
import com.yoonlee3.diary.user.User;

@Service
public class GroupAchivService {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	
	@Autowired GroupAchivRepository groupAchivRepository;
	@Autowired GoalSatusService goalSatusService;
	@Autowired GoalService goalService;
	@Autowired GroupService groupService;
	@Autowired GroupBadgeHistoryService historyService;
	
	public GroupAchiv insertGroupAchiv(YL3Group group) {

		Set<User> users = group.getUsers();
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	@Autowired
	GroupAchivRepository groupAchivRepository;
	@Autowired
	GoalSatusService goalSatusService;
	@Autowired
	GoalService goalService;
	@Autowired
	GroupService groupService;
	@Autowired
	GroupBadgeHistoryService historyService;

	public GroupAchiv insertGroupAchiv(YL3Group group) {

		Set<User> users = group.getUsers();

		int successGoal = 0;
		int successUser = 0;
		int successGroup = 0;

<<<<<<< HEAD
		// 그룹의 하루 성공 여부
		int groupSize = group.getUsers().size();
		if (successUser / groupSize > 0.5) {
			successGroup++;
		}

		// 그룹의 한 달 성공 여부 = 그룹의 성공한 하루 수 / 한달 일 수
		// 이번달의 마지막 날짜 = 이번 달의 일 수 
		int lastDay = YearMonth.now().lengthOfMonth();
		// 그룹의 성공한 하루 수 / 한달 일 수
		double groupAchivement = (double) successGroup / lastDay;
		
		if (groupAchivement > 0.6) {
			// 그룹 뱃지 상승
			groupService.updateGroupBadge(group.getId());
			// GroupBadgeHistory에 저장
			historyService.insertHistory(group, group.getBadge());
		}
		
		GroupAchiv groupAchiv = new GroupAchiv();
		groupAchiv.setGroup(group);
		groupAchiv.setMonth(currentDate);
		groupAchiv.setGoal_achievement(groupAchivement);
		return groupAchivRepository.save(groupAchiv);
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
		
=======

	@Autowired
	GroupAchivRepository groupAchivRepository;
	@Autowired
	GoalSatusService goalSatusService;
	@Autowired
	GoalService goalService;
	@Autowired
	GroupService groupService;
	@Autowired
	GroupBadgeHistoryService historyService;

	public GroupAchiv insertGroupAchiv(YL3Group group) {

		Set<User> users = group.getUsers();

>>>>>>> 64f87d4 (0422)
		int successGoal = 0;
		int successUser = 0;
		int successGroup = 0;

=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
		LocalDate currentDate = LocalDate.now();
		for (User user : users) {
			int goalSize = groupAchivRepository.findNowGoalSize(user.getId(), currentDate);
			// 해당 유저의 목표 리스트 가져오기
			List<Goal> goals = goalService.findByUserId(user);
			// 해당 유저의 성공한 목표 수
			for (Goal goal : goals) {
				successGoal = goalSatusService.findTodaySuccess(goal);
			}
			if (successGoal / goalSize >= 0.5) {
				successUser++;
			}
		}

		// 그룹의 하루 성공 여부
		int groupSize = group.getUsers().size();
		if (successUser / groupSize > 0.5) {
			successGroup++;
		}

		// 그룹의 한 달 성공 여부
		int lastDay = YearMonth.now().lengthOfMonth();
		// 그룹의 성공한 하루 수 / 한달 일 수
		double groupAchivement = (double) successGroup / lastDay;

		if (groupAchivement > 0.6) {
			// 그룹 뱃지 상승
			groupService.updateGroupBadge(group.getId());
			// GroupBadgeHistory에 저장
			historyService.insertHistory(group, group.getBadge());
		}

		GroupAchiv groupAchiv = new GroupAchiv();
		groupAchiv.setGroup(group);
		groupAchiv.setMonth(currentDate);
		groupAchiv.setGoal_achievement(groupAchivement);
		return groupAchivRepository.save(groupAchiv);
	}

}
