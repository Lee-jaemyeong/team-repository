package com.yoonlee3.diary.group_achiv;

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
public class Group_achiv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long group_achiv_id;
	
	@ManyToOne
	@Column(nullable=false)
	@JoinColumn(name = "group_id")
	private YL3Group group_id;
	
	@Column(nullable=false)
	private Date month;
	
	@Column(nullable=false)
	private Double goal_achievement;

}
