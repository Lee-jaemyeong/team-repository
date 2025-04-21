package com.yoonlee3.diary.badge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {
	
	@Autowired
	BadgeRepository badgeRepository;
	
	public Badge selectById(Long badge_id) {
		return badgeRepository.selectById(badge_id);
	}
}
