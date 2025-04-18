package com.yoonlee3.diary.badge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {
	
	@Autowired
	BadgeRepository badgeRepository;
	
	
}
