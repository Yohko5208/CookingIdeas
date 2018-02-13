package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DownloadController {
    @GetMapping("download")
    String home(HttpSession session) {
        return "redirect:https://drive.google.com/uc?export=download&id=1fcID6wUwGTTLlR0bfTSE35joXQV9wO_C";
    }
}
