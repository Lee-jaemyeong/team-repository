package com.yoonlee3.diary.user;

import java.util.List;

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
<<<<<<< HEAD
<<<<<<< HEAD
	public User selectUser(Long user_id) {
		return userRepository.findById(user_id).get();
	}

=======

	public User findById(Long user_id) {
		return userRepository.findById(user_id).get();
	}

=======

	public User findById(Long user_id) {
		return userRepository.findById(user_id).get();
	}

>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("해당 유저는 존재하지 않습니다."));
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

<<<<<<< HEAD
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	// update
	public int updateByPass(User user) {
		return userRepository.updateByIdAndPassword(user.getPassword(), user.getEmail());
	}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public int updateByUsername(Long user_id, User user ) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}	
	
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	public int updateByUsername(Long user_id, User user) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}

<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

	public int updateByUsername(Long user_id, User user) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}

=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	// delete
	public int deleteByEmailAndPassword(String password, String email) {
		return userRepository.deleteByIdAndPassword(password, email);
	}

<<<<<<< HEAD
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
}
