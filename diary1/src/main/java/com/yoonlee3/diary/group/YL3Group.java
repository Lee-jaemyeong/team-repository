package com.yoonlee3.diary.group;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.yoonlee3.diary.badge.Badge;
import com.yoonlee3.diary.diary.Diary;
import com.yoonlee3.diary.groupAchiv.GroupAchiv;
import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistory;
import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class YL3Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Long id;
<<<<<<< HEAD
<<<<<<< HEAD
	
	@Column(unique=true , nullable=false)  
=======

	@Column(unique = true, nullable = false)
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

	@Column(unique = true, nullable = false)
>>>>>>> 64f87d4 (0422)
	private String group_title;

	@Column(nullable = false)
	private String group_content;

	@Column(updatable = false)
	private LocalDateTime create_date = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "group_leader")
	private User group_leader;

	@OneToOne
	@JoinColumn(name = "badge_id")
	private Badge badge;
<<<<<<< HEAD
<<<<<<< HEAD
	
	
=======

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

>>>>>>> 64f87d4 (0422)
	@ManyToMany
	@JoinTable(name = "Group_has_User", // 조인 테이블 이름
			joinColumns = @JoinColumn(name = "group_id"), // 현재 엔티티(PK)
			inverseJoinColumns = @JoinColumn(name = "user_id") // 상대 엔티티(PK)
	)
	private Set<User> users = new HashSet<>();
<<<<<<< HEAD
<<<<<<< HEAD
	
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE )
    private List<GroupBadgeHistory> badgeHistories = new ArrayList<>();
	
	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE )
=======
=======
>>>>>>> 64f87d4 (0422)

	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
	private List<GroupBadgeHistory> badgeHistories = new ArrayList<>();

	@OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	private List<GroupAchiv> groupAchiv = new ArrayList<>();

	
=======
	private List<GroupAchiv> groupAchiv = new ArrayList<>();

>>>>>>> 64f87d4 (0422)
}