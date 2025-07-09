package com.example.ss.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.ss.domain.UserDomain;

@Mapper
public interface UserMapper {
	// 회원가입, 로그인
		int insert(UserDomain userDomain);
	
	
	// 회원탈퇴
		int deleteByUsername(String username);

	
	// 회원조회
		UserDomain selectByUsername(String username);
	
	
	// 회원수정
		int update(UserDomain userDomain);
}
