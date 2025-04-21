package com.yoonlee3.diary.groupDiary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.diary.Diary;
import com.yoonlee3.diary.group.YL3Group;

@Service
public class GroupDiaryService {
	
	@Autowired
	GroupDiaryRepository groupDiaryRepository;
	
	public GroupDiary insertGroupDiary(YL3Group group, Diary diary ) {
		GroupDiary groupDiary = new GroupDiary();
		groupDiary.setGroup(group);
		groupDiary.setDiary(diary);
		return groupDiaryRepository.save(groupDiary);
	}
	
	public int deleteGroupDiary(YL3Group group, Diary diary) {
		return groupDiaryRepository.deleteGroupDiary(group.getId() , diary.getId());
	}
	
}
