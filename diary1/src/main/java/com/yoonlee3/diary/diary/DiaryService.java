package com.yoonlee3.diary.diary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
<<<<<<< HEAD
	@Autowired DiaryRepository diaryRepository;
	
	public List<Diary> findAll() { 
		return diaryRepository.findAllByOrderByDesc();
=======

	@Autowired
	DiaryRepository diaryRepository;

	public List<Diary> findAll() {
		return diaryRepository.findAll();
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}

	@Transactional
	public Diary find(Long diary_id) {
		Diary diary = diaryRepository.findById(diary_id).get();
		return diary;
	}

	public void insert(Diary diary) {
		diaryRepository.save(diary);
	}
<<<<<<< HEAD
	
	public Diary update_view(Long id) {
		return diaryRepository.findById(id).get(); 
=======

	public Diary update_view(Long diary_id) {
		return diaryRepository.findById(diary_id).get();
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}

	public int update(Diary diary) {
<<<<<<< HEAD
		return diaryRepository.updateById(
			diary.getId(),diary.getDiary_title(),diary.getDiary_content()
		);
	}
	
	public int delete(Diary diary) { 
=======
		return diaryRepository.updateById(diary.getId(), diary.getDiary_title(), diary.getDiary_content());
	}

	public int delete(Diary diary) {
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
		return diaryRepository.deleteByDId(diary.getId());
	}
}
