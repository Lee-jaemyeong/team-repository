package com.yoonlee3.diary;

import com.yoonlee3.diary.jw.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    // 유저 10명 추가 테스트
    @Test
    public void addUsers() {
        // 10명의 유저를 추가합니다.
        for (int i = 1; i <= 10; i++) {
            String username = "user" + i;
            String email = "user" + i + "@example.com";
            String password = "password" + i;

            // User 객체 생성
            User user = new User(username, email, password);

            // User 객체를 저장
            userRepository.save(user);
        }

        // 저장된 유저 확인 (Optional)
        for (int i = 1; i <= 10; i++) {
            User user = userRepository.findByUsername("user" + i);
            System.out.println("Saved user: " + user.getUsername() + ", Email: " + user.getEmail());
        }
    }
}
