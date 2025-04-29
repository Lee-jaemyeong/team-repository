package com.yoonlee3.diary.badge;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.openScope.OpenScope;

@Service
public class BadgeService {
	
	@Autowired
	BadgeRepository badgeRepository;
	
	public Optional<Badge> findById(Long badge_id) {
		return badgeRepository.findById(badge_id);
	}
	
	@PostConstruct
    public void initializeOpenScopes() {
        // 이미 공개범위가 존재하는지 확인 (중복 방지)
        if (badgeRepository.count() == 0) {

            Badge firstBadge = new Badge();
            firstBadge.setBadge_title("씨앗");

            Badge secondBadge = new Badge();
            secondBadge.setBadge_title("새싹");

            Badge thirdBadge = new Badge();
            thirdBadge.setBadge_title("줄기");

            Badge fourthBadge = new Badge();
            fourthBadge.setBadge_title("떡잎");

            Badge fifthBadge = new Badge();
            fifthBadge.setBadge_title("나무");
            
            // 공개 범위 값을 데이터베이스에 저장
            badgeRepository.save(firstBadge);
            badgeRepository.save(secondBadge);
            badgeRepository.save(thirdBadge);
            badgeRepository.save(fourthBadge);
            badgeRepository.save(fifthBadge);
            
            System.out.println("초기 뱃지가 데이터베이스에 저장되었습니다.");
        }
    }
}
