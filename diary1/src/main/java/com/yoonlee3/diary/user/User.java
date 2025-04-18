package com.yoonlee3.diary.user;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.yoonlee3.diary.group.YL3Group;

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
	
	@OneToMany(mappedBy = "group_leader")
	private Set<YL3Group> ledGroups = new HashSet<>();
	
	@ManyToMany(mappedBy = "users")
	private Set<YL3Group> groups = new HashSet<>();
	
}

