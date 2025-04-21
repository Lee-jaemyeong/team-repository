package com.yoonlee3.diary.diary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

	@Autowired
	DiaryRepository diaryRepository;

	public List<Diary> findAll() {
		return diaryRepository.findAll();
	}

	@Transactional
	public Diary find(Long diary_id) {
		Diary diary = diaryRepository.findById(diary_id).get();
		diaryRepository.save(diary);
		return diary;
	}

	public void insert(Diary diary) {
		diaryRepository.save(diary);
	}

	public Diary update_view(Long diary_id) {
		return diaryRepository.findById(diary_id).get();
	}

	public int update(Diary diary) {
		return diaryRepository.updateById(diary.getId(), diary.getDiary_title(), diary.getDiary_content());
	}

	public int delete(Diary diary) {
		return diaryRepository.deleteByDId(diary.getId());
	}
}
