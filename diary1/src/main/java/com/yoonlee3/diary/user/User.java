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

import com.yoonlee3.diary.group.YL3Group;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		
	@Column(unique=true , nullable=false)  
=======

	@Column(unique = true, nullable = false)
>>>>>>> 64f87d4 (0422)
	private String username;
	@Column(nullable = false)
	private String password;

	private String nickname;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(updatable = false, nullable = false)
	private LocalDateTime create_date = LocalDateTime.now();

	@ManyToMany(mappedBy = "users")
	private Set<YL3Group> groups = new HashSet<>();
<<<<<<< HEAD
}
=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	private String nickname;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(updatable = false, nullable = false)
	private LocalDateTime create_date = LocalDateTime.now();

	@ManyToMany(mappedBy = "users")
	private Set<YL3Group> groups = new HashSet<>();
<<<<<<< HEAD
=======

>>>>>>> 64f87d4 (0422)
=======

>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
}
