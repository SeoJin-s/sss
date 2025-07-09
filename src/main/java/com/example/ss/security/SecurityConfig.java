package com.example.ss.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.ss.domain.UserDomain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	// springSecurity 필터들을 Bean 으로 등록 -> 체인형태로 등록
	// HttpRequest 를 가로채서 랩핑하여 springSecurity 에 사용가능한 HttpSecurity 타입을 변환시켜 인자로 받음
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 System.out.println("🔥 Security 설정 진짜 들어옴?");
		
		// 인자값(HttpSecurity) 을 이용하여 인증, 인가를 설정
		
	
	// 1) CSRF 설정이 기본값이 true - form 에서 값을 넘길때 약손된 암호화 된 토큰 값을 넘거야한다.
	// 	  SpringSecurity 가 가진 설정값 중 CSRF 설정정보를 가지는  CsrfConfigure  정보값을 변경
	httpSecurity.csrf((csrfConfigure) -> csrfConfigure.disable());
	
	// 2) 인가 설정
	// SpringSecurity 가 가진 설정값 중 AuthorizationManagerRequestMatcherRegistry (인가 리스트) 설정 정보를 수정
	httpSecurity.authorizeHttpRequests((requestMatcherRegistry) -> 
			requestMatcherRegistry.requestMatchers("/", "/login", "/loginAction", "/addUser", "/addUserAction", "/WEB-INF/view/**").permitAll()
								  .requestMatchers("/admin/**").hasRole("ADMIN")	// 테이블 role 컬럼 값이 "ROLE_ADMIN"
								  .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
								  //.requestMatchers("/user/**").hasRole("USER")		// 테이블 role 컬럼 값이 "ROLE_USER"
								  .anyRequest().authenticated());
	
	// 3) 로그인 설정
	// FormLoginConfigurer 설정값을 변경
	httpSecurity.formLogin((FormLoginConfigurer) -> 
			FormLoginConfigurer.loginPage("/login")
							   .loginProcessingUrl("/loginAction")	
							   // 인증을 위해 필터를 가로채는 loginAction 주소 -> UserDetailService를 생성
							   .successHandler(new AuthenticationSuccessHandler() {
						            @Override
						            public void onAuthenticationSuccess(HttpServletRequest request,
						                                                HttpServletResponse response,
						                                                Authentication authentication) throws IOException, ServletException {
						                System.out.println("로그인 성공 유저: " + authentication.getName());
						                // request 값을 가공, response
						                response.sendRedirect("/");
						            }
						        })
						        .failureHandler(new AuthenticationFailureHandler() {
						            @Override
						            public void onAuthenticationFailure(HttpServletRequest request,
						                                                HttpServletResponse response,
						                                                AuthenticationException exception) throws IOException, ServletException {
						                System.out.println("로그인 실패: " + exception.getMessage());
						                response.sendRedirect("/login?error=true");
						            }
						        }));
	
	// 4) 로그아웃 설정
	httpSecurity.logout((logoutConfigurer) -> 
			logoutConfigurer.logoutUrl("/logout")
							.invalidateHttpSession(true)	
							.logoutSuccessUrl("/"));
		
	
		return httpSecurity.build();	// httpSecurity 설정이 끝나면 리빌드하여 securityFilterChain 타입으로 반환

	}
}