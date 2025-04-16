package com.yoonlee3.diary.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.user.User;

@Service
public class GroupService {
	
	@Autowired GroupRepository groupRepository;
	
	//insert
	public Group insertGroup(Group group) {
		return groupRepository.save(group);
	}
	
	//read
	public List<Group> findAll(){
		return groupRepository.findAll();
	}
	
	public List<Group> selectByGroupTitle(String group_title) {
		return groupRepository.selectByGroupTitle(group_title);
	}
	
	
	 public List<Group> selectByGroupLeader(Long user_id){ return
	 groupRepository.selectByGroupUser(user_id); }
	 public List<Group> selectByGroupUser(Long user_id){ return
	 groupRepository.selectByGroupUser(user_id); }
	 
	
	//update
	public int updateGroupBadge (Long group_id) {
		return groupRepository.updateGroupBadge(group_id);
	}
	
	public int updateGroup (Group group, User user) {
		return groupRepository.updateGroup(group.getGroup_title(),
					group.getGroup_content(), group.getGroup_id(), user.getUser_id());
	}
	//delete
	
	
}
