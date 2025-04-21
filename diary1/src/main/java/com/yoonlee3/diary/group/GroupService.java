package com.yoonlee3.diary.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistoryService;
import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.user.User;

@Service
public class GroupService {
	
	@Autowired GroupRepository groupRepository;
	@Autowired JoinToGroupService joinToGroupService;
	@Autowired GroupBadgeHistoryService historyService;
	
	//insert
	public YL3Group insertGroup(YL3Group group, User user) {
		group.setGroup_leader(user);
		joinToGroupService.joinToGroup(group.getId(), user.getId());
		return groupRepository.save(group);
	}

	//read
	public List<YL3Group> findAll(){
		return groupRepository.findAll();
	}
	
	public List<YL3Group> findByGroupTitle(String group_title) {
		return groupRepository.findByGroupTitle(group_title);
	}
	
	//update
	public int updateGroupBadge (Long group_id) {
		return groupRepository.updateGroupBadge(group_id);
	}
	
	public int updateGroup (YL3Group group, User user) {
		return groupRepository.updateGroup(group.getGroup_title(),
					group.getGroup_content(), group.getId(), user.getId());
	}
	
	//delete
	public int deleteGroup (Long group_id, Long user_id) {
		return groupRepository.deleteGroup(group_id, user_id);
	}
	
}
