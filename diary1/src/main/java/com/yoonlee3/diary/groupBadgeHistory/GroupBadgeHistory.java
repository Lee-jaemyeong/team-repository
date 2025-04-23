package com.yoonlee3.diary.groupBadgeHistory;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yoonlee3.diary.badge.Badge;
import com.yoonlee3.diary.group.YL3Group;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GroupBadgeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_badge_history_id")
	private Long id;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	
=======

>>>>>>> 64f87d4 (0422)
	@ManyToOne
	@JoinColumn(name = "group_id")
	private YL3Group group;

	@ManyToOne
	@JoinColumn(name = "badge_id")
	private Badge badge;
<<<<<<< HEAD
	
	@Column(updatable = false , nullable=false)
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	@ManyToOne
	@JoinColumn(name = "group_id")
	private YL3Group group;

	@ManyToOne
	@JoinColumn(name = "badge_id")
	private Badge badge;

	@Column(updatable = false, nullable = false)
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

	@Column(updatable = false, nullable = false)
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	private LocalDateTime create_date = LocalDateTime.now();

}
