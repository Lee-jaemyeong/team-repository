package com.yoonlee3.diary.badge;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {
	
	@Autowired
	BadgeRepository badgeRepository;
	
	public Optional<Badge> findById(Long badge_id) {
		return badgeRepository.findById(badge_id);
	}
	
}
