package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping ("/login")
    String login(Model model, HttpSession session, HttpServletRequest req){
        model.addAttribute("user", new User());
        session.setAttribute("referer", req.getHeader("Referer"));
        return "login";
    }
}
