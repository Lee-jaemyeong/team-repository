package com.yoonlee3.diary.diary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.template.Template;
import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id;
	
	@Column(nullable=false)
	private String diary_title;
	@Column(nullable=false)
	private String diary_content;
	
	@Column(nullable = false )
	private String diary_emoji;
	
	@Column(updatable = false)
	private LocalDateTime create_date = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToOne
	@JoinColumn(name = "open_scope_id")
	private OpenScope openScope;

	@OneToOne
	@JoinColumn(name = "template_id")
	private Template template;
}
