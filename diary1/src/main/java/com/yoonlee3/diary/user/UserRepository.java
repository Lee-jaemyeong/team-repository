package com.yoonlee3.diary.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// 2. read
	Optional<User> findByEmail(String email);

	@Query("select u from User u where u.username= :username")
	User findByUsername(String username);

	// 3. update
	@Modifying
	@Transactional
	@Query("update User u set u.username= :username where u.id= :user_id ")
	int updateById(Long user_id, String username);

	@Modifying
	@Transactional
	@Query("update User u set u.password= :password where u.email= :email ")
	int updateByIdAndPassword(String password, String email);
}
