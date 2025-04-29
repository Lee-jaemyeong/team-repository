package com.yoonlee3.diary.groupBadgeHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.badge.Badge;
import com.yoonlee3.diary.group.YL3Group;

@Service
public class GroupBadgeHistoryService {
	
	@Autowired
	GroupBadgeHistoryRepository badgeHistoryRepository;
	
	public  GroupBadgeHistory insertHistory(YL3Group group, Badge badge ) {
		GroupBadgeHistory history = new GroupBadgeHistory();
		history.setGroup(group);
		history.setBadge(badge);
		return badgeHistoryRepository.save(history);
	}
}
