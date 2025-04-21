package com.yoonlee3.diary;

import com.yoonlee3.diary.jw.*;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FollowTest {

    @Autowired UserRepository userRepository;
    @Autowired FollowRepository followRepository;

    // 팔로우 추가 테스트
    //@Disabled 
    //@Test
    public void insert() {
        // 예시로 10명의 사용자 (user1, user2, ..., user10)를 대상으로 팔로우 관계 20개 생성
        for (int followerId = 1; followerId <= 10; followerId++) {
            for (int followingId = 1; followingId <= 10; followingId++) {
                // follower와 following이 동일하지 않도록 설정
                if (followerId != followingId) {
                    String followerUsername  = "user" + followerId;
                    String followingUsername = "user" + followingId;

                    // User 객체를 가져옵니다.
                    User follower  = userRepository.findByUsername(followerUsername);
                    User following = userRepository.findByUsername(followingUsername);

                    // Follow 객체를 생성하고 설정
                    Follow follow = new Follow();
                    follow.setFollower(follower);
                    follow.setFollowing(following);

                    // Follow 객체를 저장
                    followRepository.save(follow);
                    System.out.println(followerUsername + "가 " + followingUsername + "을(를) 팔로우했습니다.");
                }
            }
        }
    }
    
    // 내가 팔로우한 사람들 목록 조회 테스트
    //@Disabled 
    //@Test
    public void findFollowings() {
        // 특정 사용자 (user2)를 찾아서 그 사용자가 팔로우하는 사람들 목록을 가져옵니다.
        User follower = userRepository.findByUsername("user2");

        // user2가 팔로우한 사람들을 가져오는 쿼리
        List<Follow> followings = followRepository.findByFollower(follower);

        System.out.println("user2가 팔로우한 사람들:");
        for (Follow follow : followings) {
            System.out.println(follow.getFollowing().getUsername());
        }
    }

    // 나를 팔로우한 사람들 목록 조회 테스트
    //@Disabled 
    //@Test
    public void findFollowers() {
        // 특정 사용자 (user1)를 찾아서 그 사용자를 팔로우하는 사람들 목록을 가져옵니다.
        User following = userRepository.findByUsername("user1");

        // user1을 팔로우하는 사람들을 가져오는 쿼리
        List<Follow> followers = followRepository.findByFollowing(following);

        System.out.println("user1을 팔로우한 사람들:");
        for (Follow follow : followers) {
            System.out.println(follow.getFollower().getUsername());
        }
    }

    // 언팔로우 테스트
    //@Disabled 
    //@Test
    public void delete() {
        // 먼저 팔로우 관계를 설정
        User follower = userRepository.findByUsername("user2");
        User following = userRepository.findByUsername("user3");

        // Follow 객체 찾기
        Follow follow = followRepository.findByFollowerAndFollowing(follower, following);

        // 팔로우 관계 삭제
        if (follow != null) {
            followRepository.delete(follow);
            System.out.println("언팔로우 성공했습니다.");
        } else {
            System.out.println("팔로우 관계가 존재하지 않습니다.");
        }
    }
    
    // 팔로워 수와 팔로잉 수 테스트
    //@Disabled 
    @Test
    public void countAllUsersFollowersAndFollowings() {
        for (int i = 1; i <= 10; i++) {
            String username = "user" + i;
            User user = userRepository.findByUsername(username);

            if (user == null) {
                System.out.println(username + " 유저를 찾을 수 없습니다.");
                continue;
            }

            long followerCount = followRepository.countByFollowing(user);  // 나를 팔로우한 사람들
            long followingCount = followRepository.countByFollower(user);  // 내가 팔로우한 사람들

            System.out.println("[" + username + "]");
            System.out.println("  팔로워 수: " + followerCount);
            System.out.println("  팔로잉 수: " + followingCount);
        }
    }
    
}