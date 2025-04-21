package com.yoonlee3.diary.jw;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
		
	@Column(unique=true , nullable=false)  
	private String username;
	@Column(nullable=false)
	private String password;
		
	@Column(unique=true , nullable=false)
	private String email;
	
	@Column(nullable = true)
	private String profileImageUrl;
		
	@Column(updatable = false , nullable=false)
	private LocalDateTime create_date = LocalDateTime.now();
	
	@OneToMany(mappedBy = "follower")
    private List<User> follower = new ArrayList<>();

    @OneToMany(mappedBy = "following")
    private List<User> following  = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email    = email;
        this.password = password;
    }
	
	
}

