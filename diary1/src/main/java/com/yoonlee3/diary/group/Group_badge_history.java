package com.yoonlee3.diary.group;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.yoonlee3.diary.badge.Badge;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Group_badge_history {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long group_badge_history_id;
	
	@ManyToOne
	private Group group_id;
	
	@OneToOne
	private Badge badge_id;
	
	@Column(updatable = false , nullable=false)
	private LocalDateTime create_date = LocalDateTime.now();

}