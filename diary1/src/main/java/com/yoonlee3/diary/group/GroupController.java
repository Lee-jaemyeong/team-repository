package com.yoonlee3.diary.group;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

@Controller
public class GroupController {

	@Autowired
	GroupService groupService;
	@Autowired
	UserRepository userRepository;

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

	/*
	 * // 그룹 가입하기 화면(post)
	 * 
	 * @PostMapping("group/join") public String groupJoin_post(Principal principal,
	 * Group group, User user, Group_has_User gu) { String username =
	 * principal.getName(); User user = userRepository.findByUsername(username);
	 * 
	 * gu.setGroup_id(group.getGroup_id()); return "group/group"; }
	 */

	// 그룹 탈퇴하기 화면(get)
	@GetMapping("group/leave")
	public String groupLeave_get() {
		return "group/leave";
	}

	// 그룹 탈퇴하기 화면(post)
	/*
	 * @PostMapping("group/leave") public String groupLeave_Post(Principal
	 * principal, Group group, Group_has_User gu) { String username =
	 * principal.getName(); User user = UserRepository; // 여기서 user_id 포함됨
	 * gu.setGroup_id(group.getGroup_id()); gu.setUser_id(user.getId());
	 * guService.delete(gu); return "group/group"; }
	 */
	// 그룹 삭제하기 화면(get)
	// 그룹 삭제하기 화면(post)

}
