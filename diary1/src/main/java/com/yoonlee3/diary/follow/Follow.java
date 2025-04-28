package com.yoonlee3.diary.follow;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yoonlee3.diary.follow.FollowId;
import com.yoonlee3.diary.user.User;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(FollowId.class)
@Getter
@Setter
@Builder
public class Follow {
	
	@Id
	@ManyToOne
	@JoinColumn(name="follower_id", nullable=false)
	private User follower;
	
	@Id
	@ManyToOne
	@JoinColumn(name="following_id", nullable=false)
	private User following;
	
	@Column(nullable = false, updatable = false)
	private final LocalDateTime createdAt = LocalDateTime.now();

	public Follow(FollowId id, User follower, User following) {
        this.follower  = follower;
        this.following = following;
    }

}
