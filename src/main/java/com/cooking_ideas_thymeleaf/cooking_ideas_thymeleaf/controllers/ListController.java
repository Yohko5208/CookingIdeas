package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ListController {
    @RequestMapping(value="list",params="type", method= RequestMethod.GET)
    String plat(HttpSession session, Model model, @RequestParam("type") int type) {
        System.out.println("OK");
        try{
            Plat p= new Plat();
            List<Plat> list = new ArrayList<>();
            User u=(User)session.getAttribute("user");
            if(type==0){
                list = p.getAllPlat();
            }
            else{
                list=u.getPlatUser(type);
            }

            model.addAttribute("liste", list);
            System.out.println("YESS!!");
            return "list";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }
}
