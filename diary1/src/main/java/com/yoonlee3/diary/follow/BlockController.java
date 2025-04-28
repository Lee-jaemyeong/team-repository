package com.yoonlee3.diary.follow;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;
import com.yoonlee3.diary.user.UserService;

@RestController
@RequestMapping("/block")
public class BlockController {

    private final BlockService blockService;
    private final UserService userService;
    private final UserRepository userRepository;

    public BlockController(BlockService blockService, UserService userService, UserRepository userRepository) {
        this.blockService = blockService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 차단
    @PostMapping
    public ResponseEntity<String> block(@RequestParam Long blockerId, @RequestParam Long blockedId) {
        User blocker = userRepository.findById(blockerId)
                .orElseThrow(() -> new RuntimeException("차단자 없음"));
        User blocked = userRepository.findById(blockedId)
                .orElseThrow(() -> new RuntimeException("차단된 사용자 없음"));

        blockService.blockUser(blocker, blocked);
        return ResponseEntity.ok("차단 성공");
    }

    // 차단 해제
    @DeleteMapping
    public ResponseEntity<String> unblock(@RequestParam Long blockerId, @RequestParam Long blockedId) {
        User blocker = userRepository.findById(blockerId)
                .orElseThrow(() -> new RuntimeException("차단자 없음"));
        User blocked = userRepository.findById(blockedId)
                .orElseThrow(() -> new RuntimeException("차단된 사용자 없음"));

        blockService.unblockUser(blocker, blocked);
        return ResponseEntity.ok("차단 해제 성공");
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
        if (principal == null) {
            return "redirect:/user/login"; // 로그인 안 한 경우
        }

        // 로그인한 사용자의 이메일로 사용자 정보 조회
        User currentUser = userService.findByEmail(principal.getName());
        Long currentUserId = currentUser.getId();

        model.addAttribute("currentUserId", currentUserId);

        // userId가 없으면 현재 로그인한 사용자로 설정
        if (userId == null || userId.equals(currentUserId)) {
            userId = currentUserId;
        }

        List<User> blockedUsers = blockService.getBlockedUsers(currentUserId);
        Set<Long> blockedUserIds = blockedUsers.stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        model.addAttribute("blockedUsers", blockedUsers);
        model.addAttribute("blockedUserIds", blockedUserIds);
    
        return "block/list"; // block/list.html 페이지로 리턴
    }
}
