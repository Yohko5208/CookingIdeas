package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;

        import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("home")
    String home(HttpSession session, Model model) {
        Plat p=new Plat();
        try{
            model.addAttribute("platAime", p.getMostLiked());
            model.addAttribute("platRealise", p.getMostRealised());
            return "home";
        }
        catch(Exception e){
            return"error";
        }
    }
}
