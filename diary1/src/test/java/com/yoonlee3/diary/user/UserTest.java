package com.yoonlee3.diary.user;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.yoonlee3.diary.Diary1Application;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

@SpringBootTest(classes = Diary1Application.class)
@Rollback(false)
public class UserTest {
	
	@Autowired UserRepository userRepository;

	@Disabled
	public void insertUser2() {
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@admin.com");
		user.setPassword("1234");
		user.setUsername("admin");
		user.setNickname("admin");
		
		userRepository.save(user);
		/////////
		User user2 = new User();
		user2.setUsername("test");
		user2.setEmail("test@test.com");
		user2.setPassword("test");
		user2.setNickname("test");
		
		userRepository.save(user2);
		//////////////////
		User user3 = new User();
		user3.setUsername("apple");
		user3.setEmail("apple@test.com");
		user3.setPassword("1111");
		user3.setNickname("1111");
		
		userRepository.save(user3);
		//////////////
		User user4 = new User();
		user4.setUsername("banana");
		user4.setEmail("banana@test.com");
		user4.setPassword("2222");
		user4.setNickname("2222");
		
		userRepository.save(user4);
		////////////////
		User user5 = new User();
		user5.setUsername("mango");
		user5.setEmail("mango@test.com");
		user5.setPassword("3333");
		user5.setNickname("5555");
		
		userRepository.save(user5);
	}
}

