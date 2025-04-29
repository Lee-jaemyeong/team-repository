package com.yoonlee3.diary.follow;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yoonlee3.diary.diary.DiaryRepository;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;
import com.yoonlee3.diary.user.UserService;

@Controller
@RequestMapping("/block")
public class BlockController {

	private final BlockService blockService;
	private final UserService userService;
	private final UserRepository userRepository;
	private final FollowService followService;
	private final FollowRepository followRepository;
	private final DiaryRepository diaryRepository;

	public BlockController(BlockService blockService, UserService userService, UserRepository userRepository,
			FollowService followService, FollowRepository followRepository, DiaryRepository diaryRepository) {
		this.blockService = blockService;
		this.userService = userService;
		this.userRepository = userRepository;
		this.followService = followService; 
		this.followRepository = followRepository; 
		this.diaryRepository = diaryRepository; 
	}

	// 차단
    @PostMapping
    public ResponseEntity<String> block(@RequestParam Long blockerId, @RequestParam Long blockedId) {
        User blocker = userRepository.findById(blockerId)
                .orElseThrow(() -> new RuntimeException("차단자 없음"));
        User blocked = userRepository.findById(blockedId)
                .orElseThrow(() -> new RuntimeException("차단된 사용자 없음"));

        blockService.blockUser(blocker, blocked);
        
        // 차단과 동시에 팔로우 언팔로우 처리
        followService.unfollow(blocker, blocked);  // 팔로우 해제
        
        return ResponseEntity.ok("차단 및 언팔로우 성공");
    }

    // 차단 해제
    @DeleteMapping
    public ResponseEntity<String> unblock(@RequestParam Long blockerId, @RequestParam Long blockedId) {
        User blocker = userRepository.findById(blockerId)
                .orElseThrow(() -> new RuntimeException("차단자 없음"));
        User blocked = userRepository.findById(blockedId)
                .orElseThrow(() -> new RuntimeException("차단된 사용자 없음"));

        blockService.unblockUser(blocker, blocked);

        // 차단 해제 시 팔로우 복원
        followService.follow(blocker, blocked);  // 팔로우 다시 하기
        
        return ResponseEntity.ok("차단 해제 및 팔로우 복원 성공");
    }

    // 차단 여부 확인
    @GetMapping("/isBlocked")
    public ResponseEntity<Boolean> isBlocked(@RequestParam Long blockerId, @RequestParam Long blockedId) {
        User blocker = userRepository.findById(blockerId)
                .orElseThrow(() -> new RuntimeException("차단자 없음"));
        User blocked = userRepository.findById(blockedId)
                .orElseThrow(() -> new RuntimeException("차단된 사용자 없음"));

        boolean isBlocked = blockService.isBlocked(blocker, blocked);
        return ResponseEntity.ok(isBlocked);
    }
    
    // 내가 차단한 사용자 목록
    @GetMapping("/list")
    public String blockPage(Model model, Principal principal, @RequestParam(value = "userId", required = false) Long userId) {
        
    	model.addAttribute("isMyPage", true);
    	
    	if (principal == null) {
            return "redirect:/user/login"; // 로그인 안 한 경우
        }

        // 로그인한 사용자의 이메일로 사용자 정보 조회
        String email = principal.getName();
        User currentUser = userService.findByEmail(email); // 이메일로 사용자 조회
        Long currentUserId = currentUser.getId();

        // 로그인한 사용자의 닉네임을 모델에 추가
        String nickname = currentUser.getUsername();  // 닉네임 또는 사용자 이름을 가져옴
        if (nickname == null || nickname.isEmpty()) {
            nickname = "익명 사용자";  // 닉네임이 없을 경우 기본값 설정
        }

        // 팔로우 관련 데이터
        List<Follow> followers = followRepository.findByFollowing(currentUser);  // 나를 팔로우한 사람들
        List<Follow> followings = followRepository.findByFollower(currentUser);   // 내가 팔로우한 사람들

        // 팔로워 수와 팔로잉 수 계산
        long followerCount = followRepository.countByFollowing(currentUser);
        long followingCount = followRepository.countByFollower(currentUser);

        // 차단된 사용자 목록 가져오기
        List<User> blockedUsers = blockService.getBlockedUsers(currentUserId);
        Set<Long> blockedUserIds = blockedUsers.stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        // 작성한 일기 수 가져오기
        long diaryCount = diaryRepository.countByUser(currentUser); // 일기 작성 수        
        
        // 모델에 필요한 데이터 추가
        model.addAttribute("nickname", nickname);  // 닉네임
        model.addAttribute("followers", followers);  // 팔로워
        model.addAttribute("followings", followings);  // 팔로잉
        model.addAttribute("followerCount", followerCount);  // 팔로워 수
        model.addAttribute("followingCount", followingCount);  // 팔로잉 수
        model.addAttribute("currentUserId", currentUserId);  // 현재 사용자 ID
        model.addAttribute("blockedUsers", blockedUsers);  // 차단된 사용자 목록
        model.addAttribute("blockedUserIds", blockedUserIds);  // 차단된 사용자 ID 목록

        return "block/list"; // block/list.html 페이지로 리턴
    }
}
