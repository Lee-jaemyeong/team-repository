package com.yoonlee3.diary.groupHasUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.group.GroupRepository;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

@Service
public class JoinToGroupService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;

	@Autowired
	public JoinToGroupService(GroupRepository groupRepository, UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		this.groupRepository = groupRepository;
	}

	// 그룹 참여하기
	@Transactional
	public void joinToGroup(Long group_id, Long user_id) {
		YL3Group group = groupRepository.findById(group_id).orElseThrow(() -> new RuntimeException("그룹 없음"));
		User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("유저 없음"));

		group.getUsers().add(user);
		user.getGroups().add(group);

		int currentSize = group.getUsers().size();
		if (currentSize >= 8) {
			throw new IllegalStateException("그룹 인원이 최대 8명을 초과할 수 없습니다.");
		}
		groupRepository.save(group);
	}

	// 그룹 떠나기
	@Transactional
	public void leaveGroup(Long group_id, Long user_id) {
		YL3Group group = groupRepository.findById(group_id).orElseThrow(() -> new RuntimeException("그룹 없음"));
		User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("유저 없음"));

		if (!group.getUsers().contains(user)) {
			throw new IllegalStateException("해당 유저는 그룹에 속해있지 않습니다.");
		} else if (group.getGroup_leader().equals(user)) {
			throw new IllegalStateException("그룹 리더는 그룹을 탈퇴할 수 없습니다. ");
		} else {
			group.getUsers().remove(user);
			user.getGroups().remove(group);
		}
		groupRepository.save(group);
	}

	// 그룹 수 확인하기
	@Transactional
	public int checkGroupSize(Long group_id) {
		YL3Group group = groupRepository.findById(group_id).orElseThrow();
		return group.getUsers().size();
	}

}
