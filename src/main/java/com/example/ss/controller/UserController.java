package com.example.ss.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ss.domain.UserDomain;
import com.example.ss.dto.UserDto;
import com.example.ss.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	
	@PostMapping("/addUserAction")
	public String addUserAction(UserDto userDto) {
		userService.addUser(userDto);
		return "redirect:/login";
	}
	// 수정
	@GetMapping("/user/updateForm")
	public String updateForm(Model model, Principal principal) {
	    String username = principal.getName();
	    UserDomain user = userService.getUser(username);
	    model.addAttribute("user", user);
	    return "updateForm";
	}
	// 수정
	@PostMapping("/user/update")
	public String update(UserDomain user) {
	    userService.updateUser(user);
	    return "redirect:/";
	}

	// 탈퇴, 세션 정리
	@GetMapping("/user/delete")
	public String delete(Principal principal, HttpSession session) {
	    String username = principal.getName();
	    userService.deleteUser(username);

	    // 세션과 시큐리티 컨텍스트 초기화 ✨
	    session.invalidate();
	    SecurityContextHolder.clearContext();

	    return "redirect:/";
	}
}
