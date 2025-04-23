package com.yoonlee3.diary.user;

import lombok.Getter;

@Getter
public enum UserRole {
<<<<<<< HEAD
=======
	
	ADMIN("ROLE_ADMIN") , USER("ROLE_USER");
	private String value;
>>>>>>> 64f87d4 (0422)
	
	ADMIN("ROLE_ADMIN") , USER("ROLE_USER");
	private String value;
	
	private UserRole(String value) { this.value = value; }
}
