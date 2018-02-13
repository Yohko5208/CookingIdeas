package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DisconnectController {
    @GetMapping("disconnect")
    String home(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:home";
    }
}
