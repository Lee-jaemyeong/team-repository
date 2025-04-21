package com.yoonlee3.diary.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
	
	@Autowired
	TemplateRepositroy templateRepositroy;
	
	public Template selectById(Long template_id ) {
		return templateRepositroy.selectById(template_id);
	}
}
