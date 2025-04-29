package com.yoonlee3.diary.group;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yoonlee3.diary.groupAchiv.GroupAchiv;
import com.yoonlee3.diary.groupAchiv.GroupAchivRepository;

@SpringBootTest
public class GroupAchivTest {
	
	@Autowired
	GroupAchivRepository groupAchivRepository;
	@Autowired
	GroupRepository groupRepository;
	
	@Test
	void insert() {
		
		GroupAchiv groupAchiv = new GroupAchiv();
		YL3Group group = groupRepository.findById(1l).orElseThrow();
		groupAchiv.setGroup(group);
		
		LocalDate date = LocalDate.now();
		groupAchiv.setMonth(date);
		
		groupAchiv.setGoal_achievement(95.55);
		
		groupAchivRepository.save(groupAchiv);
		
	}
	
	
}
