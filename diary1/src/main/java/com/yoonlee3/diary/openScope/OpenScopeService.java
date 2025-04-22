package com.yoonlee3.diary.openScope;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenScopeService {

	@Autowired
	OpenScopeRepository openScopeRepository;

<<<<<<< HEAD
	public OpenScope findOpenScope(OpenScope openScope) {
		return openScopeRepository.findByOpenScopeId(openScope.getId());
=======
	public OpenScope selectOpenScope(OpenScope openScope) {
		return openScopeRepository.selectById(openScope.getId());
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	}

}
