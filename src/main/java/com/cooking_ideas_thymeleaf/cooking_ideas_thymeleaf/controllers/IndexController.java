package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
class IndexController {

    @GetMapping("/")
    String index(Model model) {
            return "redirect:home";
    }
}
