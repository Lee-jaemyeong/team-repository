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
	
	//insert
	public User insertUser(User user) { 
		user.setPassword(passwordEncoder.encode( user.getPassword() ));
		return userRepository.save(user);
	}
	//selectAll
	public List<User> selectUserAll(){
		return userRepository.findAll();
	}
	
	//select
	public User selectUser(Long user_id) {
		return userRepository.findById(user_id).get();
	}
	
	//update
	public int updateByPass( User user ) {
		return userRepository.updateByIdAndPassword(user.getPassword(), user.getEmail());
	}
	public int updateByUsername( User user , String oldname ) {
		return userRepository.updateById(oldname, user.getUsername());
	}	
	
	//delete
	public int deleteUser(User user) { 
		return userRepository.deleteByIdAndPassword(user.getUser_id(), user.getPassword());
	}
}
