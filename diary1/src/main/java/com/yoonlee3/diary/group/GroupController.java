package com.yoonlee3.diary.group;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yoonlee3.diary.groupDiary.GroupDiary;
import com.yoonlee3.diary.groupDiary.GroupDiaryService;
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
	@Autowired
	GroupDiaryService groupDiaryService;

	
	// 그룹 화면
	@GetMapping("group/group/{id}")
	public String groupPage( @PathVariable("id") Long group_id, Model model ) {
		model.addAttribute("isGroupPage", true);
		System.out.println("그룹 아이디 잘 넘어왔니..................?" + group_id);
		YL3Group group = groupService.findById(group_id);
		System.out.println("그룹 찾았니..........................?" +  group );
		// 그룹 정보 보내기
		model.addAttribute("group", group);
		// 그룹 소속 유저들 보내기
		Set<User> users = group.getUsers();
		System.out.println("유저 찾았니......................?" + users);
		model.addAttribute("users", users);
		// 그룹의 다이어리 보내기
		List<GroupDiary> diary = groupDiaryService.findByGroupId(group);
		System.out.println("다이어리 찾았니......................?" + diary);
		model.addAttribute("diary", diary);
		return "group/group";
	}
	
	// 그룹 가입하기 화면(get)
	@GetMapping("group/join")
	public String groupJoin_get() {
		return "group/join";
	}
	
	
	// 그룹 화면
	@GetMapping("group/group")
	public String groupget() {
		return "group/group";
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
	
	// 그룹 수정하기
	@GetMapping("gorup/update")
	public String gorupUpdate_get(YL3Group group, Model model ) {
		YL3Group findGroup = groupService.findById(group.getId());
		model.addAttribute("findGroup", findGroup);
		return "user/mypage";
	}
	
	// 그룹 수정하기
	@PostMapping("gorup/update/{id}")
	public String gorupUpdate_post(@PathVariable("id") Long gorup_id, Principal principal, @RequestParam String group_title, @RequestParam String group_content) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		YL3Group group = groupService.findById(gorup_id);
		group.setGroup_title(group_title);
		group.setGroup_content(group_content);
		groupService.updateGroup(group, user);
		return "user/mypage";
	}
	
	// 그룹 생성하기 화면(get)
	public String groupInset_get() {
		return "group/insert";
	}
	
	// 그룹 생성하기 화면(post)
	@PostMapping("group/insert")
	public String groupInsert_post(Principal principal, YL3Group group) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		groupService.insertGroup(group, user);
		return "user/mypage";
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

}
