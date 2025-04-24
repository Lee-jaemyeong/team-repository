package com.yoonlee3.diary.group;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoonlee3.diary.groupHasUser.JoinToGroupService;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserService;

@Controller
public class GroupController {

	@Autowired
	GroupService groupService;
	@Autowired
	UserService userService;
	@Autowired
	JoinToGroupService joinToGroupService;

	// 그룹 화면
	@GetMapping("group/group")
	public String groupPage() {
		return "group/group";
	}

	// 그룹 가입하기 화면(get)
	@GetMapping("group/join")
	public String groupJoin_get() {
		return "group/join";
	}
	
	// 그룹 가입하기 화면(post)
	@PostMapping("group/join")
	public String groupJoin_post(Principal principal, YL3Group group, User user) {
		String username = principal.getName();
		user = userService.findByUsername(username);
		Long user_id = user.getId();
		Long group_id = group.getId();
		joinToGroupService.joinToGroup(group_id, user_id);
		return "group/group";
	}
	
	
	// 그룹 탈퇴하기 화면(get)
	@GetMapping("group/leave")
	public String groupLeave_get() {
		return "group/leave";
	}
	
	// 그룹 탈퇴하기 화면(post)
	@PostMapping("group/leave")
	public String groupLeave_Post(Principal principal, YL3Group group, User user) {
		String username = principal.getName();
		user = userService.findByUsername(username);
		Long user_id = user.getId();
		Long group_id = group.getId();
		
		joinToGroupService.leaveGroup(group_id, user_id);
		return "group/group";
	}
	
	// 그룹 삭제하기 화면(get)
	@GetMapping("group/delete")
	public String groupDelete_get() {
		return "group/delete";
	}
	
	// 그룹 삭제하기 화면(post)
	@PostMapping("group/delete")
	public String groupDelete_post(Principal principal, YL3Group group, User user) {
		String username = principal.getName();
		user = userService.findByUsername(username);
		Long user_id = user.getId();
		Long group_id = group.getId();
		
		groupService.deleteGroup(group_id, user_id);
		return "group/main";
	}

	@GetMapping("/groupmain")
	public String goMain() {
	    return "group/Gmain";
	}
	
}
