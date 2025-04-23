package com.yoonlee3.diary.jw;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    
    // 팔로우 기능
    @Transactional
    public void follow(User follower, User following) {
        if (!followRepository.existsByFollowerAndFollowing(follower, following)) {
            followRepository.save(
                Follow.builder()
                        .follower(follower)
                        .following(following)
                        .build()
            );
        }
    }

   // 내가 팔로우한 사람들
    public List<Follow> getFollowings(User follower) {
        return followRepository.findByFollower(follower);
    }

    // 나를 팔로우한 사람들
    public List<Follow> getFollowers(User following) {
        return followRepository.findByFollowing(following);
    }	

    // 언팔로우 기능
    @Transactional
    public void unfollow(User follower, User following) {
        followRepository.deleteByFollowerAndFollowing(follower, following);
    }
    
    // 팔로우 여부 확인
    public boolean isFollowing(User loginUser, User targetUser) {
        return followRepository.existsByFollowerAndFollowing(loginUser, targetUser);
    }

    public UserProfileDto getUserProfile(User viewer, User target) {
        long followers = followRepository.countByFollowing(target);  // 팔로워 수
        long followings = followRepository.countByFollower(target);  // 팔로잉 수
        
        // 프로필 이미지 URL 설정 (기본값 설정)
        String image = target.getProfileImageUrl() != null ? target.getProfileImageUrl() : "https://cdn.example.com/default-profile.png";
        
        // 로그인한 사용자가 해당 사용자를 팔로우 중인지 여부
        boolean isFollowing = followRepository.existsByFollowerAndFollowing(viewer, target);

        // UserProfileDto 반환
        return new UserProfileDto(
            target.getUser_id(),
            target.getUsername(),
            image,
            followers,
            followings,
            isFollowing
        );
    }
}