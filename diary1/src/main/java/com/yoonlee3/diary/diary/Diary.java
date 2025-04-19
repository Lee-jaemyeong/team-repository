package com.yoonlee3.diary.diary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diary_id;
	
	@Column(nullable=false)
	private String diary_title;
	@Column(nullable=false)
	private String diary_content;
	
	@ManyToOne  @JoinColumn(name="USER_ID")
	private User user;
}
