package com.yoonlee3.diary.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GroupRepository extends JpaRepository<Group, Long>{
	
	// 1. insert
	
	// 2. read
	@Modifying
	@Transactional
	@Query("select g from Group g where g.group_title= :group_title")
	List<Group> selectByGroupTitle(String group_title);
	
	@Modifying
	@Transactional
	@Query("select g from Group g where g.group_leader=: user_id")
	List<Group> selectByGroupLeader(Long user_id);
	
	
	 @Modifying 
	 @Transactional
	 @Query("select gu from Group_has_User gu where gu.user_id= : user_id")
	 List<Group> selectByGroupUser(Long user_id);
	 
	
	// 3. update
	@Modifying
	@Transactional
	@Query("update Group g set g.badge_id = g.badge_id +1 where g.group_id= :group_id")
	int updateGroupBadge (@Param("group_id") Long group_id);
	
	@Modifying
	@Transactional
	@Query("update Group g set g.group_title= :group_title, group_content= :group_content"+
			" where g.group_id= :group_id and g.group_leader= :user_id")
	int updateGroup (String group_title, String group_content, Long group_id, Long user_id);
	
	
	// delete
	@Modifying
	@Transactional
	@Query("delete from Group g where g.group_id= :group_id and g.group_leader = :user_id")
	int deleteGroup(Long group_id, Long user_id);
	
}
