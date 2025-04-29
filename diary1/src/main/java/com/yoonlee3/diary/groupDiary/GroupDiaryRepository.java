package com.yoonlee3.diary.groupDiary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupDiaryRepository extends JpaRepository<GroupDiary, Long> {

	// C

	// R
	@Query("select gd from GroupDiary gd where gd.group.id = :group_id")
	List<GroupDiary> findByGroupId(Long group_id);
	
	@Query("select gd from GroupDiary gd where gd.diary.id = :diary_id")
	GroupDiary findByDiaryId(Long diary_id);
	
	// U

	// D
	@Modifying
	@Transactional
	@Query("delete from GroupDiary gd where gd.id = :groupDiry_id")
	int deleteGroupDiary(Long groupDiry_id);

}
