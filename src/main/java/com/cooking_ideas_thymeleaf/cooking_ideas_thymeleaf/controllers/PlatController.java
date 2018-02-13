package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PlatController {
    @RequestMapping(value="plat",params="id", method= RequestMethod.GET)
    String plat(HttpSession session, Model model, @RequestParam("id") String platid) {
        System.out.println("OK");
            try{
                Plat p=new Plat();
                System.out.println("OK");
                model.addAttribute("plat", p.parseJson(p.getPLatById(platid)));
                if(session.getAttribute("user")!=null){
                    session.setAttribute("user", ((User)session.getAttribute("user")).refresh());
                }

                System.out.println("YESS!!");
                return "plat";
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                return "error";
            }
    }
}
