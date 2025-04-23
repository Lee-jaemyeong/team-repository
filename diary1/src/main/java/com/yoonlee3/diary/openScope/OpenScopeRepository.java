package com.yoonlee3.diary.openScope;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenScopeRepository extends JpaRepository<OpenScope, Long> {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	//C
	//R
	@Query("select os from OpenScope os where os.id = :open_scope_id")
	OpenScope findByOpenScopeId(Long open_scope_id);
	//U
	//D
=======
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	// C

	// R
	@Query("select os from OpenScope os where os.id = :open_scope_id")
<<<<<<< HEAD
	OpenScope selectById(Long open_scope_id);
=======

	// C

	// R
	@Query("select os from OpenScope os where os.id = :open_scope_id")
	OpenScope findByOpenScopeId(Long open_scope_id);
>>>>>>> 64f87d4 (0422)
=======
	OpenScope findByOpenScopeId(Long open_scope_id);
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe

	// U

	// D
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
}
