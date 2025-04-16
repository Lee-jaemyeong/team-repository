package com.yoonlee3.diary.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
		
	@Column(unique=true , nullable=false)  
	private String username;
	@Column(nullable=false)
	private String password;
		
	@Column(unique=true , nullable=false)
	private String email;
		
	@Column(updatable = false , nullable=false)
	private LocalDateTime create_date = LocalDateTime.now();
}

