package com.yoonlee3.diary.groupBadgeHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupBadgeHistoryRepository extends JpaRepository<GroupBadgeHistory, Long> {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	//C
	//R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> findGroupBadgeHistory(Long group_id);
	//U
	//D
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	// C
	
	// R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> findGroupBadgeHistory(Long group_id);
	
	// U
	
	// D
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
	// C
	
	// R
	@Query("select gbh from GroupBadgeHistory gbh where gbh.group.id = :group_id ")
	List<GroupBadgeHistory> findGroupBadgeHistory(Long group_id);
	
	// U
	
	// D
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
}
