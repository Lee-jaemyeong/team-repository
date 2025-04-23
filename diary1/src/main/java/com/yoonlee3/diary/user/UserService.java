package com.yoonlee3.diary.user;

import java.util.List;
import java.util.Optional;

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

	public User findById(Long user_id) {
		return userRepository.findById(user_id).get();
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// update
	public int updateByPass(User user) {
		return userRepository.updateByIdAndPassword(user.getPassword(), user.getEmail());
	}

	public int updateByUsername(Long user_id, User user) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}

	// delete
	public int deleteByEmailAndPassword(String password, String email) {
		return userRepository.deleteByIdAndPassword(password, email);
	}

}
