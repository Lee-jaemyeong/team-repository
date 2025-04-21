package com.yoonlee3.diary.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class UserSecurity {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				// admin만 접근가능
				// .requestMatchers( new AntPathRequestMatcher("/admin/**") )
				// .hasRole("ROLE_ADMIN") // ADMIN 역할
				// member만 접근가능
				// .requestMatchers( new AntPathRequestMatcher("/member/**") )
				// .hasRole("ROLE_MEMBER") // MEMBER 역할
				// 기타페이지 모두 접근가능( 로그인 필요 없음 )
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll() // 모든사용자 접근가능
		).formLogin( // login
				(formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/user/mypage")).logout( // logout
						(logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
								.logoutSuccessUrl("/user/login").invalidateHttpSession(true));
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
