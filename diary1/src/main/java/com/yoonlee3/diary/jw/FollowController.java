package com.yoonlee3.diary.jw;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
   

    @PostMapping
    public ResponseEntity<String> follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("팔로워 없음"));
        User following = userRepository.findById(followingId).orElseThrow(() -> new RuntimeException("팔로잉 없음"));

        followService.follow(follower, following);
        return ResponseEntity.ok("팔로우 성공");
    }
    
 // 내가 팔로우한 사람 목록
    @GetMapping("/following")
    public ResponseEntity<List<String>> getFollowing(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<String> followings = followService.getFollowings(user)
                .stream()
                .map(f -> f.getFollowing().getUsername())
                .collect(Collectors.toList());
        return ResponseEntity.ok(followings);
    }

    // 나를 팔로우한 사람 목록
    @GetMapping("/followers")
    public ResponseEntity<List<String>> getFollowers(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<String> followers = followService.getFollowers(user)
                .stream()
                .map(f -> f.getFollower().getUsername())
                .collect(Collectors.toList());
        return ResponseEntity.ok(followers);
    }


    @DeleteMapping
    public ResponseEntity<String> unfollow(@RequestParam Long followerId, @RequestParam Long followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("팔로워 없음"));
        User following = userRepository.findById(followingId).orElseThrow(() -> new RuntimeException("팔로잉 없음"));

        followService.unfollow(follower, following);
        return ResponseEntity.ok("언팔로우 성공");
    }
    
    // 사용자 프로필 조회 (팔로워/팔로잉 수 포함)
    @GetMapping("/all-profiles")
    public ResponseEntity<List<UserProfileDto>> getAllUserProfiles() {
        List<User> allUsers = userRepository.findAll();
        List<UserProfileDto> profiles = allUsers.stream().map(user -> {
            // 팔로워 수와 팔로잉 수 계산
            long followers = followRepository.countByFollowing(user);
            long followings = followRepository.countByFollower(user);

            // 프로필 이미지 URL 처리 (이미지 URL이 없으면 기본값 사용)
            String image = user.getProfileImageUrl() != null 
                ? user.getProfileImageUrl() 
                : "https://cdn.example.com/default-profile.png";

            // UserProfileDto 객체 생성
            return new UserProfileDto(
                user.getUser_id(), 
                user.getUsername(), 
                image,  // 프로필 이미지 URL 추가
                followers, 
                followings
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(profiles);
    }
}
