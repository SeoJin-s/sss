package com.example.ss.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ss.domain.UserDomain;
import com.example.ss.dto.UserDto;
import com.example.ss.mapper.UserMapper;

@Service
public class UserService {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserMapper userMapper;
	
	public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userMapper = userMapper;
	}
	
	public void addUser(UserDto userDto) {
		if(userMapper.selectByUsername(userDto.getUsername()) != null) {
			System.out.println(userDto.getPassword()+ "사용자 이름이 존재합니다.");
			// throw new RuntimeException("사용자 이름이 존재합니다.");
			return;
		}
		
		
		// dto -> domain
		UserDomain userDomain = new UserDomain();
		userDomain.setUsername(userDto.getUsername());
		userDomain.setRole("ROLE_USER"); // ROLE_ADMIN, ROLE_USER
		userDomain.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userMapper.insert(userDomain);
	}
	// 수정
	public int updateUser(UserDomain user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //
	    return userMapper.update(user);
	}
	public UserDomain getUser(String username) {
        return userMapper.selectByUsername(username);
    }
	
	// 탈퇴
	public void deleteUser(String username) {
	    userMapper.deleteByUsername(username);
	}
}
