package com.yoonlee3.diary.follow;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

@RestController
@RequestMapping("/block")
public class BlockController {

    private final BlockService blockService;
    private final UserRepository userRepository;

    public BlockController(BlockService blockService, UserRepository userRepository) {
        this.blockService = blockService;
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
}
