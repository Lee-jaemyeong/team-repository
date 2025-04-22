package com.yoonlee3.diary.badge;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {
	
	@Autowired
	BadgeRepository badgeRepository;
	
<<<<<<< HEAD
	public Optional<Badge> findById(Long badge_id) {
		return badgeRepository.findById(badge_id);
	}
	
=======
	public Badge selectById(Long badge_id) {
		return badgeRepository.selectById(badge_id);
	}
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
}
