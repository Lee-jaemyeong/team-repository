package com.yoonlee3.diary.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.user.User;

@Service
public class GroupService {
	
	@Autowired GroupRepository groupRepository;
	
	//insert
	public YL3Group insertGroup(YL3Group group, User user_id) {
		group.setGroup_leader(user_id);
		return groupRepository.save(group);
	}

	//read
	public List<YL3Group> findAll(){
		return groupRepository.findAll();
	}
	
	public List<YL3Group> selectByGroupTitle(String group_title) {
		return groupRepository.selectByGroupTitle(group_title);
	}
	
	//update
	public int updateGroupBadge (Long group_id) {
		return groupRepository.updateGroupBadge(group_id);
	}
	
	public int updateGroup (YL3Group group, User user) {
		return groupRepository.updateGroup(group.getGroup_title(),
					group.getGroup_content(), group.getGroup_id(), user.getUser_id());
	}
	
	//delete
	public int deleteGroup (Long group_id, Long user_id) {
		return groupRepository.deleteGroup(group_id, user_id);
	}
	
}
