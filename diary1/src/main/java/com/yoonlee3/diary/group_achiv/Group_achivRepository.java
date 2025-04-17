package com.yoonlee3.diary.group_achiv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Group_achivRepository extends JpaRepository<Group_achiv, Long>{

	//C
	//R
	@Query("select ga from Group_achiv ga where ga.group_id = :group_id")
	List<Group_achiv> findByGroup_achiv(Long group_id);
	//U
	//D
}
