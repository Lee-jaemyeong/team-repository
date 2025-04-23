package com.yoonlee3.diary.diary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 64f87d4 (0422)
	@Query("select d from Diary d order by id desc")
	List<Diary> findAllByOrderByDesc();

	@Modifying
	@Transactional
	@Query("update Diary d set d.diary_title= :diary_title, d.diary_content= :diary_content where d.id= :id")
	int updateById(@Param("id") Long id, String diary_title, String diary_content);

	@Modifying
	@Transactional
	@Query("delete from Diary d where d.id= :id")
	int deleteByDId(Long id);
<<<<<<< HEAD
=======

	@Modifying
	@Transactional
	@Query("update Diary d set d.diary_title= :diary_title, d.diary_content= :diary_content where d.id= :diary_id")
	int updateById(Long diary_id, String diary_title, String diary_content);

	@Modifying
	@Transactional
	@Query("delete from Diary d where d.id= :diary_id")
	int deleteByDId(Long diary_id);
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

>>>>>>> 64f87d4 (0422)
}
