package com.yoonlee3.diary.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column(name = "user_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	private String nickname;
	
	private String profileImageUrl;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(updatable = false, nullable = false)
	private LocalDateTime create_date = LocalDateTime.now();

	@ManyToMany(mappedBy = "users")
	private Set<YL3Group> groups = new HashSet<>();
	
	@OneToMany(mappedBy = "follower")
    private List<User> follower = new ArrayList<>();

    @OneToMany(mappedBy = "following")
    private List<User> following  = new ArrayList<>();
    
    // blockedUsers는 차단된 사용자의 리스트
    @OneToMany(mappedBy = "blocker") 
    private Set<User> blockedUsers;

    // blocker는 차단하는 사용자
    @ManyToOne
    @JoinColumn(name = "blocker_id") 
    private User blocker;

}
