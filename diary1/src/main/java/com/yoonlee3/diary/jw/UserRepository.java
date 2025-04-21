package com.yoonlee3.diary.jw;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	 User findByUsername(String username);
	
}
