package com.yoonlee3.diary.like;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.diary.Diary;
import com.yoonlee3.diary.diary.DiaryRepository;
import com.yoonlee3.diary.user.User;
import com.yoonlee3.diary.user.UserRepository;

@Service
public class LikeService {
    @Autowired LikeRepository likeRepository;
    @Autowired DiaryRepository diaryRepository;
    @Autowired UserRepository userRepository;

    @Transactional
    public boolean toggleLike(Long diaryId, Long userId) {
        var existingLike = likeRepository.findByDiaryIdAndUserId(diaryId, userId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return false;
        } else {
            Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 없습니다."));
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다."));

            Likes like = new Likes();
            like.setDiary(diary);
            like.setUser(user);
            likeRepository.save(like);
            return true;
        }
    }

    public boolean isLiked(Long diaryId, Long userId) {
        return likeRepository.findByDiaryIdAndUserId(diaryId, userId).isPresent();
    }
    
    public long getLikeCount(Long diaryId) {
        return likeRepository.countByDiaryId(diaryId);
    }
}

