package com.yoonlee3.diary.user;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
	private Long Id;
    private String username;
    private String profileImageUrl; 
    private long followers;
    private long followings;
    private boolean isFollowing;
    private List<String> followersList;
    private List<String> followingsList;
    
    public String getProfileImageUrl() {
        if (this.profileImageUrl == null || this.profileImageUrl.isEmpty()) {
            return "/images/default-profile.png";
        }
        return this.profileImageUrl;
    }

    public UserProfileDto(Long userId, String username) {
        this.Id = userId;
        this.username = username;
        this.followers = 0;
        this.followings = 0;
        this.isFollowing = false;
        this.followersList = List.of();
        this.followingsList = List.of();
    }
    
}