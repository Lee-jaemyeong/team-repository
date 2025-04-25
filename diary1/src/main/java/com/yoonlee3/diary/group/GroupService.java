package com.yoonlee3.diary.group;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistoryService;
import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.user.User;

@Service
public class GroupService {

	@Autowired
	GroupRepository groupRepository;
	@Autowired
	JoinToGroupService joinToGroupService;
	@Autowired
	GroupBadgeHistoryService historyService;

	// insert
	public void insertGroup(YL3Group group) {
		YL3Group save = groupRepository.save(group);
		User user = save.getGroup_leader();
		System.out.println("...........................유저야......."+user);
		joinToGroupService.joinToGroup(save.getId(), user.getId());
	}

	// read
	public List<YL3Group> findAll() {
		return groupRepository.findAll();
	}

	public List<YL3Group> findByGroupTitle(String group_title) {
		return groupRepository.findByGroupTitle(group_title);
	}

	public YL3Group findById(Long group_id) {
		return groupRepository.findById(group_id).orElseThrow(() -> new RuntimeException("존재하지 않는 그룹입니다."));
	}

	// update
	public int updateGroupBadge(Long group_id) {
		return groupRepository.updateGroupBadge(group_id);
	}

	public int updateGroup(YL3Group group, User user) {
		return groupRepository.updateGroup(group.getGroup_title(), group.getGroup_content(), group.getId(),
				user.getId());
	}

	// delete
	public int deleteGroup(Long group_id, Long user_id) {
		YL3Group group = groupRepository.findById(group_id).orElseThrow(()-> new RuntimeException("여기는 GroupService............ 그룹이 존재하지 않아요 흑흑"));
		// 그룹안에 있는 유저들 가져오기
		Set<User> users = group.getUsers();
		// 그룹 떠나게 하기
		for(User u : users) {
			joinToGroupService.leaveGroup(group_id, u.getId());
		}
		return groupRepository.deleteGroup(group_id, user_id);
	}

}
