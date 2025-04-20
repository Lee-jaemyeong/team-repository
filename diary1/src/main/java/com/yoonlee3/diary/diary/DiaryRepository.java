package com.yoonlee3.diary.diary;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
	@Modifying
	@Transactional
	@Query("update Diary d set d.diary_title= :diary_title, d.diary_content= :diary_content where d.id= :diary_id")
	int updateById(Long diary_id, String diary_title, String diary_content);

	@Modifying
	@Transactional
	@Query("delete from Diary d where d.id= :diary_id")
	int deleteByDId(Long diary_id);
}
