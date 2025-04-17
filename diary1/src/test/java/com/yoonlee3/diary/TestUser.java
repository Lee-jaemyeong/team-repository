package com.yoonlee3.diary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

@SpringBootTest
public class TestUser {
	@Autowired UserRepository userRepository;
	
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("first");
		user.setPassword("1111");
		user.setEmail("first@gmail.com");
		userRepository.save(user);
	}
	
}
