package com.yoonlee3.diary.follow;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FollowId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long follower; 
	private Long following;
	
}
