package com.yoonlee3.diary.groupAchiv;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GroupAchivRepository extends JpaRepository<GroupAchiv, Long> {

<<<<<<< HEAD
	//C
	//R
	@Query("select ga from GroupAchiv ga where ga.group.id = :group_id")
	List<GroupAchiv> findByGroupAchiv(Long group_id);
	
	@Query("select count(*) from Goal g where g.user.id= :user_id and g.startDate <= :currentDate"
			+ " and g.dueDate >= :currentDate")
	int findNowGoalSize(Long user_id, LocalDate currentDate);
	
	//U
	//D
=======
	// C
	
	// R
	@Query("select ga from GroupAchiv ga where ga.group.id = :group_id")
	List<GroupAchiv> findByGroupAchiv(Long group_id);

	@Query("select count(*) from Goal g where g.user.id= :user_id and g.startDate <= :currentDate"
			+ " and g.dueDate >= :currentDate")
	int selectNowGoalSize(Long user_id, LocalDate currentDate);

	// U
	
	// D
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
}
