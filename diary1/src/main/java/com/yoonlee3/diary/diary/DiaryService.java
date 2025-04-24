package com.yoonlee3.diary.diary;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
	@Autowired DiaryRepository diaryRepository;
	
	public List<Diary> findAll() { 
	    return diaryRepository.findAllByOrderByDesc(); 
	} 
	
	@Transactional
	public Diary findById(Long diary_id) {
		Diary diary = diaryRepository.findById(diary_id).get();
		return diary;
	}
	
	public void insert(Diary diary) {
		diaryRepository.save(diary);
	}
	
	public Diary update_view(Long id) {
		return diaryRepository.findById(id).get(); 
	}
	
	public int update(Diary diary) {
		return diaryRepository.updateById(
			diary.getId(),diary.getDiary_title(),diary.getDiary_content()
		);
	}
	
	public int delete(Diary diary) { 
		return diaryRepository.deleteByDId(diary.getId());
	}
}
