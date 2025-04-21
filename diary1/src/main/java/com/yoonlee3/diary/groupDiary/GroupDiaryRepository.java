package com.yoonlee3.diary.groupDiary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupDiaryRepository extends JpaRepository<GroupDiary, Long> {

	// C

	// R

	// U

	// D
	@Modifying
	@Transactional
	@Query("delete from GroupDiary gd where gd.group.id =:group_id and gd.diary.id = :diary_id")
	int deleteGroupDiary(Long group_id, Long diary_id);

}
