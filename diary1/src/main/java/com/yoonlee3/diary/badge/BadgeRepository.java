package com.yoonlee3.diary.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
<<<<<<< HEAD
<<<<<<< HEAD
=======
	
>>>>>>> 64f87d4 (0422)
	//C
	
	//R
	@Query("select b from Badge b where b.id = :badge_id")
	Optional<Badge> findById(Long badge_id);
	
	//U
	
	//D
=======

	// C

	// R
	@Query("select b from Badge b where b.id = :badge_id")
	Badge selectById(Long badge_id);

	// U

	// D
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
}
