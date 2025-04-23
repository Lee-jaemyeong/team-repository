package com.yoonlee3.diary.groupAchiv;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.Date;
=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
import java.time.LocalDate;

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

@Entity
@Getter
@Setter
public class GroupAchiv {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_achiv_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private YL3Group group;

	@Column(nullable = false)
	private LocalDate month;

	@Column(nullable = false)
	private Double goal_achievement;

}
