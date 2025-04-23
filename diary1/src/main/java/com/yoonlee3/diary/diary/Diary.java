package com.yoonlee3.diary.diary;

import java.time.LocalDateTime;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

<<<<<<< HEAD
<<<<<<< HEAD
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

=======
import com.yoonlee3.diary.group.YL3Group;
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======
>>>>>>> 64f87d4 (0422)
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.template.Template;
import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id;
<<<<<<< HEAD
<<<<<<< HEAD
	
	@Column(nullable=false)
=======

	@Column(nullable = false)
>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
	private String diary_title;

	@Column(nullable = false)
	private String diary_content;
	
<<<<<<< HEAD
	@Column(nullable = false )
=======

	@Column(nullable = false)
	private String diary_title;
	
	@Column(nullable = false)
	private String diary_content;

	@Column(nullable = false)
>>>>>>> 64f87d4 (0422)
	private String diary_emoji;

	@Column(updatable = false)
	private LocalDateTime create_date = LocalDateTime.now();
<<<<<<< HEAD
	
=======
	@Column(nullable = false)
	private String diary_emoji;
	
	@Column(updatable = false , nullable=false)
	private LocalDateTime create_date = LocalDateTime.now();

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
=======

>>>>>>> 64f87d4 (0422)
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToOne
	@JoinColumn(name = "open_scope_id")
	private OpenScope openScope;

	@OneToOne
	@JoinColumn(name = "template_id")
	private Template template;
<<<<<<< HEAD
=======
	

>>>>>>> f6d6340bbc8f87a9c50ea7475293e98804f7b2d1
}
