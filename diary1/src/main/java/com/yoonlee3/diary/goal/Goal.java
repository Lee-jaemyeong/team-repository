package com.yoonlee3.diary.goal;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.yoonlee3.diary.goalStatus.GoalStatus;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Goal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goal_id;
	
	@Column(nullable=false)
	private String goal_content;
	
	@Column(updatable = false)
	private LocalDateTime startDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private Date due_date;
	
	@OneToOne
	@JoinColumn(name = "open_scope_id")
	private OpenScope open_scope_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user_id;
	
	/*
	@OneToMany
	List<User_achiv> userAchiv = new ArrayList<>();
	*/
	
	@OneToMany(mappedBy = "goal_id", cascade = CascadeType.REMOVE )
	List<GoalStatus> goalStatuses = new ArrayList<>();
	
}
