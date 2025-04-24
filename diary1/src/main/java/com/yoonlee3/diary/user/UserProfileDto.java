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
	private Long userId;
    private String username;
    private String profileImageUrl; 
    private long followers;
    private long followings;
    private boolean isFollowing;
    private List<String> followersList;
    private List<String> followingsList;
    
    
}