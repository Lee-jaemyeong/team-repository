package com.yoonlee3.diary.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.follow.BlockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final BlockRepository blockRepository;
	
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

	public User findByEmail(String email) {
	    return userRepository.findByEmail(email).orElse(null);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> findUsersByUsername(String username) {
	    return userRepository.findUsersByUsername(username);
	}
	
    public boolean SameUsername(String username) {
        return userRepository.existsByUsername(username);  // 닉네임 중복 확인
    }

	// update
	public int updateByPass(User user) {
		return userRepository.updateByIdAndPassword(user.getPassword(), user.getEmail());
	}

	public int updateByUsername(Long user_id, User user) {
		return userRepository.updateById(user.getId(), user.getUsername());
	}
	
	public List<User> searchUsers(String keyword) {
	    return userRepository.findByUsernameContaining(keyword); // username을 포함한 사용자 찾기
	}

	public User getCurrentUser() {
		return null;
	}

	// 내가 차단한 사용자 목록	
    public List<User> getBlockedUsers(Long currentUserId) {
        return blockRepository.findBlockedUsersByBlockerId(currentUserId);
    }	
	
}
