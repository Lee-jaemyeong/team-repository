package com.yoonlee3.diary.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query ("update User u set u.password= :password where u.email= :email and u.password= :old ")
	int updateByIdAndPassword( String password , String old , String email);
	
	@Modifying     
	@Transactional 
	@Query("delete from User u where u.user_id= :user_id and u.password= :password ")
	int deleteByIdAndPassword( Long user_id, String password );
	
	@Modifying
	@Transactional
	@Query ("update User u set u.username= :username where u.username= :old ")
	int updateById( String old , String username);
}
