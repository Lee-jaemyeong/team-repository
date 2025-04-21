package com.yoonlee3.diary.groupAchiv;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.goal.Goal;
import com.yoonlee3.diary.goal.GoalService;
import com.yoonlee3.diary.goalStatus.GoalStatusRepository;
import com.yoonlee3.diary.group.GroupRepository;
import com.yoonlee3.diary.group.GroupService;
import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistoryService;
import com.yoonlee3.diary.user.User;

@Service
public class GroupAchivService {
	
	@Autowired GroupRepository groupRepository;
	@Autowired GroupAchivRepository groupAchivRepository;
	@Autowired GoalStatusRepository goalStatusRepository;
	@Autowired GoalService goalService;
	@Autowired GroupService groupService;
	@Autowired GroupBadgeHistoryService historyService;
	
	public GroupAchiv insertGroupAchiv(YL3Group group) {

		Set<User> users = group.getUsers();
		
		int successGoal = 0;
		int successUser = 0;
		int successGroup = 0;
		LocalDate currentDate = LocalDate.now();
		for (User user : users) {
			int goalSize = groupAchivRepository.selectNowGoalSize(user.getId(), currentDate);
			// 해당 유저의 목표 리스트 가져오기
			List<Goal> goals = goalService.findByUserId(user);
			for (Goal goal : goals) {
				successGoal = goalStatusRepository.selectNowGoalSuccess(goal.getId(), currentDate);
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
		double groupAchivement = (double) successGroup / lastDay;
		if (groupAchivement > 0.6) {
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
