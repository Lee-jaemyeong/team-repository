package com.yoonlee3.diary.openScope;

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
