package com.yoonlee3.diary.template;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoonlee3.diary.openScope.OpenScope;

@Service
public class TemplateService {
	
	@Autowired
	TemplateRepositroy templateRepositroy;
	
    @PostConstruct
    public void initializeOpenScopes() {
        Template privateTemplate = new Template();
        privateTemplate.setTemplate_title("흰색");
        privateTemplate.setTemplate_content("white");
        
        templateRepositroy.save(privateTemplate);
    }
	
	public Template findTempalteById(Long template_id ) {
		return templateRepositroy.findTempalteById(template_id);
	}
}
