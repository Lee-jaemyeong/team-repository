package com.yoonlee3.diary.groupAchiv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.group.GroupRepository;
import com.yoonlee3.diary.group.YL3Group;

@Service
public class GroupAchivService {
	
	@Autowired
	GroupRepository groupRepository;
	
	public int insertGroupAchiv(YL3Group group) {
		return 0;
	}
	
}
