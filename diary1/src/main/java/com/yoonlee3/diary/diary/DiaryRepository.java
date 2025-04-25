package com.yoonlee3.diary.diary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
	
	@Query("select d from Diary d order by d.id desc")
	List<Diary> findAllByOrderByDesc();

    @Query("SELECT d FROM Diary d WHERE d.user.email = :email ORDER BY d.create_date DESC")
    List<Diary> findByUserEmail(@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query("update Diary d set d.diary_title= :diary_title, d.diary_content= :diary_content where d.id= :id")
	int updateById(@Param("id") Long id, String diary_title, String diary_content);

	@Modifying
	@Transactional
	@Query("delete from Diary d where d.id= :id")
	int deleteByDId(Long id);

}
