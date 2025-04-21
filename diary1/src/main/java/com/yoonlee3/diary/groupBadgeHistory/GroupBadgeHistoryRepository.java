package com.yoonlee3.diary.groupBadgeHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupBadgeHistoryRepository extends JpaRepository<GroupBadgeHistory, Long> {

	// C
	
	// R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> selectGroupBadgeHistory(Long group_id);

	// U

	// D
}
