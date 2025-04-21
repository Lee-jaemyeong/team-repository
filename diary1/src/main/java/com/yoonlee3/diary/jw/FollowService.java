package com.yoonlee3.diary.jw;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

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

    @Transactional
    public void unfollow(User follower, User following) {
        followRepository.deleteByFollowerAndFollowing(follower, following);
    }
    
    //  사용자 프로필 DTO
    public UserProfileDto getUserProfile(User user) {
        long followerCount = followRepository.countByFollowing(user);
        long followingCount = followRepository.countByFollower(user);

        // 프로필 이미지 URL이 없는 경우 기본 이미지 URL 설정
        String image = user.getProfileImageUrl() != null 
            ? user.getProfileImageUrl() 
            : "https://cdn.example.com/default-profile.png";

        
        return new UserProfileDto(
            user.getUser_id(),
            user.getUsername(),
            user.getProfileImageUrl(),
            followerCount,
            followingCount
        );
    }
    
}
