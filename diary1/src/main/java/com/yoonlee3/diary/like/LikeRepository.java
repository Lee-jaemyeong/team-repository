package com.yoonlee3.diary.like;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes , LikesId> {
	
	 Optional<Likes> findByDiaryIdAndUserId(Long diary_id, Long user_id);
	 
	 long countByDiaryId(Long diaryId);
	 
	 void deleteByDiaryId(Long diaryId);
}
