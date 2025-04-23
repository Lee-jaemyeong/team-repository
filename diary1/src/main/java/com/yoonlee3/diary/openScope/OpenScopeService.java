package com.yoonlee3.diary.openScope;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
>>>>>>> 64f87d4 (0422)
=======
>>>>>>> d81df584b87b2b860a5fd8f1bd8d58dff7de28fe
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenScopeService {

	@Autowired
	OpenScopeRepository openScopeRepository;

	public OpenScope findOpenScope(OpenScope openScope) {
		return openScopeRepository.findByOpenScopeId(openScope.getId());
	}

}
