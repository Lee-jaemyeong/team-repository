package com.yoonlee3.diary.openScope;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.yoonlee3.diary.goal.Goal;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class OpenScope {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long open_scope_id;
	
	@Column(nullable=false)
	private String openScope_title;	


}
