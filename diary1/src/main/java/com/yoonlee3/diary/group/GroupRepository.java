package com.yoonlee3.diary.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GroupRepository extends JpaRepository< YL3Group, Long>{
	
	// Group
	// 1. insert
	
	// 2. read
	@Query("select g from YL3Group g where g.group_title = :group_title")
	List<YL3Group> selectByGroupTitle(String group_title);
	
	@Query("select g from YL3Group g where g.group_leader = :user_id")
	List<YL3Group> selectByGroupLeader(Long user_id);
	
	// 3. update
	@Modifying
	@Transactional
	@Query("update YL3Group g set g.badge_id = g.badge_id +1 where g.group_id = :group_id")
	int updateGroupBadge (@Param("group_id") Long group_id);
	
	@Modifying
	@Transactional
	@Query("update YL3Group g set g.group_title = :group_title, group_content = :group_content"+
			" where g.group_id = :group_id and g.group_leader = :user_id")
	int updateGroup (String group_title, String group_content, Long group_id, Long user_id);
	
	// delete
	@Modifying
	@Transactional
	@Query("delete from YL3Group g where g.group_id = :group_id and g.group_leader = :user_id")
	int deleteGroup(Long group_id, Long user_id);
	
	
}
