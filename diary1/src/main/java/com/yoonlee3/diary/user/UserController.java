package com.yoonlee3.diary.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired UserService service;
	
	@GetMapping("/")
	public String main() { return "user/login"; }

	@GetMapping("/user/mypage")
	public String mypage() { return "user/mypage"; }	
	
	@GetMapping("/user/login")
	public String login() { return "user/login"; }
	
	@GetMapping("/user/join")
	public String join(UserForm userForm) { return "user/join"; }
	
	@PostMapping("/user/join")
	public String join(@Valid UserForm userForm , BindingResult bindingResult) { 
		
		if(bindingResult.hasErrors()) { return "user/join"; }
		if( !userForm.getPassword().equals(userForm.getPassword2()) ) {
			bindingResult.rejectValue("password2", "pawordInCorrect" , "패스워드를 확인해주세요");
			return "user/join";
		}
		
		try {
			User user = new User();
			user.setUsername(userForm.getUsername());
			user.setEmail(userForm.getEmail());
			user.setPassword(userForm.getPassword());
			service.insertUser(user);
		}catch(DataIntegrityViolationException e) { // 무결성 - 중복키, 외래키제약, 데이터형식불일치
			e.printStackTrace();
			bindingResult.reject("failed" , "등록된 유저입니다.");
			return "user/join";
		} catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("failed" , e.getMessage());
			return "user/join";
		}
		return "user/login"; 
	}
	
	@GetMapping("/user/find")
	public String find() { return "user/find"; }
	
	@GetMapping("/user/passchange")
	public String passchange() { return "user/passchange"; }
	
	@GetMapping("/user/userchange")
	public String userchange() { return "user/userchange"; }
}
