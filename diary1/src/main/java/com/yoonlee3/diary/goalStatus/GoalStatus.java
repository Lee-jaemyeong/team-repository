package com.yoonlee3.diary.goalStatus;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yoonlee3.diary.goal.Goal;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class GoalStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="status_id")
	private Long id;
	
	@Column(updatable = false)
	private LocalDateTime createDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private Boolean is_success;
	
	@ManyToOne
	@JoinColumn(name = "goal_id")
	private Goal goal;
	
}
