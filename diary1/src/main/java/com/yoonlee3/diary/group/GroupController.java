package com.yoonlee3.diary.group;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yoonlee3.diary.badge.Badge;
import com.yoonlee3.diary.badge.BadgeService;
import com.yoonlee3.diary.diary.Diary;
import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistoryService;
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
	@Autowired
	BadgeService badgeService;

	// 로그인 된 유저 닉네임 설정
	@ModelAttribute
	public void NicknameToModel(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
			User user = userService.findByEmail(email);
			model.addAttribute("nickname", user.getUsername());
			model.addAttribute("user", user);

			Set<YL3Group> groups = joinToGroupService.findGroupById(user.getId());
			model.addAttribute("groups", groups);
		} else {
			model.addAttribute("nickname", "Guest");
			model.addAttribute("groups", Collections.emptySet());
		}
	}

	// 그룹 홈 화면
	@GetMapping("group/main")
	public String grouHome(Model model, Principal principal) {
		List<YL3Group> groups = groupService.findAll();
		model.addAttribute("groupList", groups);
		model.addAttribute("isGroupPage", true);
		return "group/main";
	}

	// 그룹 화면
	@GetMapping("group/group/{id}")
	public String groupPage(Principal principal, @PathVariable("id") Long group_id, Model model, @ModelAttribute("turnMessage") String turnMessage) {
		model.addAttribute("isGroupPage", true);
		YL3Group group = groupService.findById(group_id);

		List<User> users = group.getUsers();

		// turn 자동 넘기기
		LocalDate today = LocalDate.now();
		if (group.getLastTurnDate() == null || group.getLastTurnDate().isBefore(today)) {
			// 오늘로 업데이트
			group.setLastTurnDate(today);

			// currentTurn + 1
			int nextTurn = (group.getCurrentTurn() + 1) % users.size();
			group.setCurrentTurn(nextTurn);
		}

		// 그룹 정보 보내기
		model.addAttribute("group", group);
		// 그룹 소속 유저들 보내기
		model.addAttribute("users", users);
		// 그룹의 다이어리 보내기
		List<GroupDiary> groupDiaryList = groupDiaryService.findByGroupId(group);
		model.addAttribute("groupDiaryList", groupDiaryList);

		if ( turnMessage != null && !turnMessage.isEmpty()) {
			model.addAttribute("turnMessage", turnMessage);
		}

		return "group/group";
	}

	// 그룹 가입하기 화면(get)
	@GetMapping("group/join")
	public String groupJoin_get() {
		return "group/join";
	}

	// 그룹 가입하기 화면(post)
	@PostMapping("group/join/{id}")
	public String groupJoin_post(Principal principal, @PathVariable("id") Long group_id,
			RedirectAttributes redirectAttributes) throws IOException {
		String email = principal.getName();
		User user = userService.findByEmail(email);
		int result = joinToGroupService.joinToGroup(group_id, user.getId()); // 한 번만 호출

		if (result == 0) {
			redirectAttributes.addFlashAttribute("message", "가입 성공!");
		} else if (result == 1) {
			redirectAttributes.addFlashAttribute("message", "그룹은 8명을 초과할 수 없습니다.");
		} else if (result == 2) {
			redirectAttributes.addFlashAttribute("message", "이미 가입된 그룹입니다.");
		}

		return "redirect:/group/main";
	}

	// 그룹 수정하기
	@GetMapping("gorup/update")
	public String gorupUpdate_get(YL3Group group, Model model) {
		YL3Group findGroup = groupService.findById(group.getId());
		model.addAttribute("findGroup", findGroup);
		return "user/mypage";
	}

	// 그룹 수정하기
	@PostMapping("group/update/{id}")
	public String gorupUpdate_post(@PathVariable("id") Long group_id, Principal principal,
			@RequestParam String group_title, @RequestParam String group_content) {

		String email = principal.getName();
		User user = userService.findByEmail(email);

		YL3Group group = groupService.findById(group_id);
		group.setGroup_title(group_title);
		group.setGroup_content(group_content);

		groupService.updateGroup(group, user);
		return "redirect:/user/mypage";
	}

	// 그룹 생성하기 화면(get)
	public String groupInset_get() {
		return "group/insert";
	}

	// 그룹 생성하기 화면(post)
	@PostMapping("group/insert")
	public String groupInsert_post(Principal principal, @RequestParam String group_title,
			@RequestParam String group_content) {

		String email = principal.getName();
		User user = userService.findByEmail(email);

		YL3Group group = new YL3Group();

		group.setGroup_title(group_title);
		group.setGroup_content(group_content);
		Badge badge = badgeService.findById(1l).orElseThrow();
		group.setBadge(badge);
		group.setGroup_leader(user);

		groupService.insertGroup(group);
		return "redirect:/user/mypage";
	}

	// 그룹 탈퇴하기 화면(get)
	@GetMapping("group/leave")
	public String groupLeave_get() {
		return "group/leave";
	}

	// 그룹 탈퇴하기 화면(post)
	@PostMapping("group/leave/{id}")
	public String groupLeave_Post(Principal principal, @PathVariable("id") Long group_id,
			RedirectAttributes redirectAttributes) {
		String email = principal.getName();
		User user = userService.findByEmail(email);

		YL3Group group = groupService.findById(group_id);

		int result = joinToGroupService.leaveGroup(group, user);
		if (result == 0) {
			redirectAttributes.addFlashAttribute("message", " 탈퇴되었습니다. ");
		} else if (result == 1) {
			redirectAttributes.addFlashAttribute("message", " 해당 그룹에 가입되어 있지 않습니다.");
		} else if (result == 2) {
			redirectAttributes.addFlashAttribute("message", " 그룹 리더는 그룹을 탈퇴할 수 없습니다.");
		}

		return "redirect:/group/main";
	}

	// 그룹 삭제하기 화면(get)
	@GetMapping("group/delete")
	public String groupDelete_get() {
		return "group/delete";
	}

	// 그룹 삭제하기 화면(post)
	@PostMapping("group/delete/{id}")
	public String groupDelete_post(Principal principal, @PathVariable("id") Long group_id, Model model) {

		String email = principal.getName();
		User user = userService.findByEmail(email);
		YL3Group group = groupService.findById(group_id);

		// 그룹 - 다이어리 연결 삭제하기
		List<GroupDiary> diaryList = groupDiaryService.findByGroupId(group);
		for (GroupDiary diary : diaryList) {
			groupDiaryService.deleteGroupDiary(diary);
		}
		groupService.deleteGroup(group);
		return "redirect:/main";
	}

	// 그룹 검색
	@GetMapping(value = "/search/group/{search}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> searchGroup(@PathVariable String search) {
		Map<String, Object> result = new HashMap<>();
		try {
			YL3Group groups = groupService.findByGroupTitle(search);

			if (groups != null) {
				result.put("groups", groups);
				result.put("status", "success");
			} else {
				result.put("status", "error");
				result.put("message", "해당 그룹을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
			result.put("message", e.getMessage());
		}
		return result;
	}

}
