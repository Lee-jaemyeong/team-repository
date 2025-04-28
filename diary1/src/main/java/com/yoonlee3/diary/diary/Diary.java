package com.yoonlee3.diary.diary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Formula;

import com.yoonlee3.diary.groupDiary.GroupDiary;
import com.yoonlee3.diary.like.Likes;
import com.yoonlee3.diary.openScope.OpenScope;
import com.yoonlee3.diary.template.Template;
import com.yoonlee3.diary.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id;

	@Column(nullable = false)
	private String diary_title;
	
	@Column(nullable = false)
	private String diary_content;

	@Column(nullable = false)
	private String diary_emoji;

	@Column(updatable = false)
	private LocalDateTime create_date = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToOne
	@JoinColumn(name = "open_scope_id")
	private OpenScope openScope;

	@OneToOne
	@JoinColumn(name = "template_id")
	private Template template;
	
	@OneToMany(mappedBy = "diary")
	private List<GroupDiary> groupDiaries;
	
	////////////////////////////////////
	@OneToMany(mappedBy = "diary")
	private List<Likes> likes=new ArrayList<>();
	
	/*@Formula("select count(*)from likes where likes.diary_id = diary_id")
	private int likeCount;*/
	
}
