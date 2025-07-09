package com.example.ss.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";  // 로그인 페이지
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // <- 이거 안 써도 Security logout 사용 가능
        return "redirect:/login";
    }
}

