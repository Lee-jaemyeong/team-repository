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

	@ManyToOne
	@JoinColumn(name = "group_id")
	private YL3Group group;

	@ManyToOne
	@JoinColumn(name = "badge_id")
	private Badge badge;

	@Column(updatable = false, nullable = false)
	private LocalDateTime create_date = LocalDateTime.now();

}
