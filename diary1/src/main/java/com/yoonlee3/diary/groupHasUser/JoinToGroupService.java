package com.yoonlee3.diary.groupHasUser;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoonlee3.diary.group.YL3Group;
import com.yoonlee3.diary.group.GroupRepository;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

@Service
public class JoinToGroupService {

	private final GroupRepository groupRepository;
	private final UserService userService;
	
	@Autowired
	public JoinToGroupService(GroupRepository groupRepository, UserService userService) {
		super();
		this.groupRepository = groupRepository;
		this.userService = userService;
	}

	// 그룹 참여하기
	@Transactional
	public int joinToGroup(Long group_id, Long user_id) {
		YL3Group group = groupRepository.findById(group_id).orElseThrow(()-> new RuntimeException("해당 그룹은 존재하지 않습니다."));
		User user = userService.findById(user_id);

		int currentSize = group.getUsers().size();
		if (currentSize >= 8) {
			return 1;
		}
		if (group.getUsers().contains(user)) {
			return 2;
		} 

		group.getUsers().add(user);
		user.getGroups().add(group);
		return 0;
	}

	// 그룹 떠나기
	@Transactional
	public int leaveGroup(YL3Group group, User user) {
		YL3Group findGroup = groupRepository.findById(group.getId()).orElseThrow(()-> new RuntimeException("해당 그룹은 존재하지 않습니다."));
		User findUser = userService.findById(user.getId());
		List<User> users = findGroup.getUsers();
		
	    int leavingIndex = -1;
	    for (int i = 0; i < users.size(); i++) {
	        if (users.get(i).getId().equals(user.getId())) {
	            leavingIndex = i;
	            break;
	        }
	    }
	    
	    if (leavingIndex == -1) {
	        return 1; // ❌ 유저가 그룹에 없음
	    }

	    if (findGroup.getGroup_leader().equals(findUser)) {
	        return 2; // ❌ 리더는 탈퇴 불가
	    }

	    // 🧹 1. 유저 삭제 먼저
	    users.remove(leavingIndex);
	    findUser.getGroups().remove(findGroup);

	    // 🧹 2. currentTurn 조정
	    if (leavingIndex < findGroup.getCurrentTurn()) {
	        findGroup.setCurrentTurn(findGroup.getCurrentTurn() - 1);
	    } else if (leavingIndex == findGroup.getCurrentTurn()) {
	        // 탈퇴한 사람이 currentTurn이면
	        // 그대로 두고, 다음 글 쓸 때 turn 넘기면 됨
	    }
	    
	    // 🧹 3. 만약 탈퇴 후 currentTurn이 리스트 크기 이상이면 0으로
	    if (findGroup.getCurrentTurn() >= users.size()) {
	        findGroup.setCurrentTurn(0);
	    }

	    return 0; // ✅ 성공
	}

	// 그룹 수 확인하기
	@Transactional
	public int checkGroupSize(Long group_id) {
		YL3Group group = groupRepository.findById(group_id).orElseThrow(()-> new RuntimeException("해당 그룹은 존재하지 않습니다."));
		return group.getUsers().size();
	}
	
	// 유저가 속한 그룹 불러오기
	@Transactional
	public Set<YL3Group> findGroupById(Long user_id){
		User user = userService.findById(user_id);
		return user.getGroups();
	}

}
