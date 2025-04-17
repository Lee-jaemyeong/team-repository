package com.yoonlee3.diary.Group_badge_history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Group_badge_historyRepository extends JpaRepository<Group_badge_history, Long> {

	//C
	//R
	@Query("select gbh from Group_badge_history where gbh.group.id = :group_id ")
	List<Group_badge_history> findGroup_badge_history(Long group_id);
	//U
	//D
}
