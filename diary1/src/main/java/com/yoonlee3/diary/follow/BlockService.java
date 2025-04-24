package com.yoonlee3.diary.follow;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoonlee3.diary.user.User;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    // 차단 기능
    @Transactional
    public void blockUser(User blocker, User blocked) {
        if (!blockRepository.existsByBlockerAndBlocked(blocker, blocked)) {
            blockRepository.save(new Block(blocker, blocked));
        }
    }

    // 차단 해제 기능
    @Transactional
    public void unblockUser(User blocker, User blocked) {
        blockRepository.deleteByBlockerAndBlocked(blocker, blocked);
    }

    // 차단 여부 확인
    public boolean isBlocked(User blocker, User blocked) {
        return blockRepository.existsByBlockerAndBlocked(blocker, blocked);
    }
}
