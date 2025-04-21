package com.yoonlee3.diary.groupAchiv;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yoonlee3.diary.group.YL3Group;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class GroupAchiv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="group_achiv_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private YL3Group group;
	
	@Column(nullable=false)
	private Date month;
	
	@Column(nullable=false)
	private Double goal_achievement;

}
