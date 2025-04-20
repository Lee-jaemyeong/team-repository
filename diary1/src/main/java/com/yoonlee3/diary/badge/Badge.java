package com.yoonlee3.diary.badge;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.yoonlee3.diary.groupBadgeHistory.GroupBadgeHistory;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Badge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="badge_id")
	private Long badge;
	
	@Column(nullable=false)
	@ColumnDefault("1")
	private String badge_title;
	
}
