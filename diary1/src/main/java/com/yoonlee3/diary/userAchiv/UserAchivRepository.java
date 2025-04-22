package com.yoonlee3.diary.userAchiv;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1

public interface UserAchivRepository extends JpaRepository<UserAchiv, Long> {

	// C

	// R
	@Query("select ua from UserAchiv ua where ua.goal.id = :goal_id")
<<<<<<< HEAD
	Optional<UserAchiv> findById(Long goal_id);
=======
	UserAchiv selectById(Long goal_id);
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1

	// U
	
	// D
}
