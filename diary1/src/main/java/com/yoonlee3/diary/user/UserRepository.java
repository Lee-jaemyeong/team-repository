package com.yoonlee3.diary.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User,Long> {  
	   // 2. read
	   Optional<User> findByEmail(String email);
	   
	   @Query("select u from User u where u.username= :username") User findByUsername(String username);
	   
	   // 3. update
	   @Modifying
	   @Transactional
	   @Query ("update User u set u.username= :username where u.id= :user_id ")
	   int updateById( Long user_id , String username);
	   
	   @Modifying
	   @Transactional
	   @Query ("update User u set u.password= :password where u.email= :email ")
	   int updateByIdAndPassword( String password , String email);
=======
public interface UserRepository extends JpaRepository<User, Long> {
	
	// 2. read
=======
public interface UserRepository extends JpaRepository<User, Long> {

	// R
>>>>>>> 64f87d4 (0422)
=======
public interface UserRepository extends JpaRepository<User, Long> {

	// R
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	Optional<User> findByEmail(String email);

	@Query("select u from User u where u.username= :username")
	User findByUsername(String username);

<<<<<<< HEAD
<<<<<<< HEAD
	// 3. update
=======
	// U
>>>>>>> 64f87d4 (0422)
=======
	// U
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
	@Modifying
	@Transactional
	@Query("update User u set u.username= :username where u.id= :user_id ")
	int updateById(Long user_id, String username);

	@Modifying
	@Transactional
	@Query("update User u set u.password= :password where u.email= :email ")
	int updateByIdAndPassword(String password, String email);
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	// D
	@Modifying
	@Transactional
	@Query("delete from User u where u.password= :password and u.email= :email ")
	int deleteByIdAndPassword(String password, String email);
	
<<<<<<< HEAD
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
}
