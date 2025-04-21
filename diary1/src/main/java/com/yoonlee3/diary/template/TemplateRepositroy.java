package com.yoonlee3.diary.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemplateRepositroy extends JpaRepository<Template, Long>{
	
	//C
	//R
	@Query("select t from Template t where t.id= :template_id")
	Template selectById(Long template_id);
	//U
	//D
}
