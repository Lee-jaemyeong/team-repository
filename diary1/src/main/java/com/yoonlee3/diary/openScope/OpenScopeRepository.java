package com.yoonlee3.diary.openScope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenScopeRepository extends JpaRepository<OpenScope, Long> {
<<<<<<< HEAD
	//C
	//R
	@Query("select os from OpenScope os where os.id = :open_scope_id")
	OpenScope findByOpenScopeId(Long open_scope_id);
	//U
	//D
=======

	// C

	// R
	@Query("select os from OpenScope os where os.id = :open_scope_id")
	OpenScope selectById(Long open_scope_id);

	// U

	// D
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
}
