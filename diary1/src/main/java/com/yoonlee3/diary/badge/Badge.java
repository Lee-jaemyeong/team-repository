package com.yoonlee3.diary.badge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.yoonlee3.diary.Group_badge_history.Group_badge_history;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long badge_id;
	
	@Column(nullable=false)
	@ColumnDefault("1")
	private String badge_title;
	
	@OneToMany
	private Group_badge_history group_badge_history;
}
