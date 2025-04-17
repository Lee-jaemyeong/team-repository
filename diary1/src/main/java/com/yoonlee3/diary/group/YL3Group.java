package com.yoonlee3.diary.group;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.yoonlee3.diary.Group_badge_history.Group_badge_history;
import com.yoonlee3.diary.badge.Badge;
import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class YL3Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long group_id;
	
	@Column(unique=true , nullable=false)  
	private String group_title;
	
	@Column(nullable=false)
	private String group_content;
	
	@Column(updatable = false)
	private LocalDateTime create_date = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "group_leader")
	@Column(nullable=false)
	private User group_leader;
	
	@OneToOne
	@JoinColumn(name = "badge_id")
	@Column(nullable=false)
	private Badge badge_id;
	
	
	@ManyToMany
	@JoinTable(
		    name = "Group_has_User", // 조인 테이블 이름
		    joinColumns = @JoinColumn(name = "group_id"), // 현재 엔티티(PK)
		    inverseJoinColumns = @JoinColumn(name = "user_id") // 상대 엔티티(PK)
		)
	private Set<User> users = new HashSet<>();
	
	@OneToMany
	private Group_badge_history group_badge_history;
	
}