package com.yoonlee3.diary.groupAchiv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupAchivRepository extends JpaRepository<GroupAchiv, Long>{

	//C
	//R
	@Query("select ga from GroupAchiv ga where ga.group_id = :group_id")
	List<GroupAchiv> findByGroupAchiv(Long group_id);
	//U
	//D
}
