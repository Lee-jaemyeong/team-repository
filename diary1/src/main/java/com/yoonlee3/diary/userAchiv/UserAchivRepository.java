package com.yoonlee3.diary.userAchiv;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserAchivRepository extends JpaRepository<UserAchiv, Long> {

	// C

	// R
	@Query("select ua from UserAchiv ua where ua.goal.id = :goal_id")
	UserAchiv selectById(Long goal_id);

	// U
	
	// D
}
