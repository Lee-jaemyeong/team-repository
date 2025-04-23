package com.yoonlee3.diary.groupAchiv;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupAchivRepository extends JpaRepository<GroupAchiv, Long> {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	//C
	//R
=======
	// C

	// R
>>>>>>> 64f87d4 (0422)
	@Query("select ga from GroupAchiv ga where ga.group.id = :group_id")
	List<GroupAchiv> findByGroupAchiv(Long group_id);

	@Query("select count(*) from Goal g where g.user.id= :user_id and g.startDate <= :currentDate"
			+ " and g.dueDate >= :currentDate")
	int findNowGoalSize(Long user_id, LocalDate currentDate);
<<<<<<< HEAD
	
	//U
	//D
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	// C

	// R
	@Query("select ga from GroupAchiv ga where ga.group.id = :group_id")
	List<GroupAchiv> findByGroupAchiv(Long group_id);

	@Query("select count(*) from Goal g where g.user.id= :user_id and g.startDate <= :currentDate"
			+ " and g.dueDate >= :currentDate")
	int findNowGoalSize(Long user_id, LocalDate currentDate);

	// U

	// D
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

	// U

	// D
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
}
