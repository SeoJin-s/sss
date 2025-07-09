package com.example.ss.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ss.domain.UserDomain;
import com.example.ss.dto.CustomerUserDetails;
import com.example.ss.mapper.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private UserMapper userMapper;
	public CustomUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
		
	}	
	// 로그인 검증하는 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDomain userDomain = userMapper.selectByUsername(username);
		// 실패하면 예외발생
		if(userDomain == null) {
			throw new UsernameNotFoundException(username+"라는 username이 없습니다.");	// 이 예외 발생시 메소드 종료 후 Spring Security / login으로 리다이렉트
			
		}
				
		// 로그인에 성공하면 UserDetails 구현체 DTO 반환
		
		return new CustomerUserDetails(userDomain);
	}

}
