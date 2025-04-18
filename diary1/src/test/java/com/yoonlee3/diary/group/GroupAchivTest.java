package com.yoonlee3.diary.group;

import java.sql.Date;

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
		groupAchiv.setGroup_id(group);
		
		Date date = Date.valueOf("2025-04-01");
		groupAchiv.setMonth(date);
		
		groupAchiv.setGoal_achievement(95.55);
		
		groupAchivRepository.save(groupAchiv);
		
	}
	
	
}
