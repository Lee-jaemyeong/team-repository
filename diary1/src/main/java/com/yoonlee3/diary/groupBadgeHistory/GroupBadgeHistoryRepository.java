package com.yoonlee3.diary.groupBadgeHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupBadgeHistoryRepository extends JpaRepository<GroupBadgeHistory, Long> {

<<<<<<< HEAD
<<<<<<< HEAD
	//C
	//R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> findGroupBadgeHistory(Long group_id);
	//U
	//D
=======
	// C
	
	// R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> selectGroupBadgeHistory(Long group_id);

	// U

	// D
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
	// C
	
	// R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> findGroupBadgeHistory(Long group_id);
	
	// U
	
	// D
>>>>>>> 64f87d4 (0422)
}
