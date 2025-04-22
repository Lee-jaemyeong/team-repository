package com.yoonlee3.diary.user;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// insert
	public User insertUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// selectAll
	public List<User> selectUserAll() {
		return userRepository.findAll();
	}

	// select
	public User selectUser(Long user_id) {
		return userRepository.findById(user_id).get();
	}

	// update
	public int updateByPass(User user) {
		return userRepository.updateByIdAndPassword(user.getPassword(), user.getEmail());
	}
<<<<<<< HEAD
	public int updateByUsername(Long user_id, User user ) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}	
	
=======

	public int updateByUsername(Long user_id, User user) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
}
