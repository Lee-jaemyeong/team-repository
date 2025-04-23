package com.yoonlee3.diary.badge;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
	
>>>>>>> 64f87d4 (0422)
=======
	
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	//C
	
	//R
	@Query("select b from Badge b where b.id = :badge_id")
	Optional<Badge> findById(Long badge_id);
	
	//U
	
	//D
}
