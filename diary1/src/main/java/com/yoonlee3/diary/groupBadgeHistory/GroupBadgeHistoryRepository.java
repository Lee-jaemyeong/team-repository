package com.yoonlee3.diary.groupBadgeHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupBadgeHistoryRepository extends JpaRepository<GroupBadgeHistory, Long> {

	//C
	//R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group_id = :group_id ")
	List<GroupBadgeHistory> findGroupBadgeHistory(Long group_id);
	//U
	//D
}
