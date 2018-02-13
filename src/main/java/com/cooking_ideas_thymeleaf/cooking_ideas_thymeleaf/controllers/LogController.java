package com.cooking_ideas_thymeleaf.cooking_ideas_thymeleaf.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LogController {

    @ModelAttribute
    public void addAttribute(Model model){
        model.addAttribute("error", "");
        model.addAttribute("success", "");
    }
    @RequestMapping(value="/login", method= RequestMethod.POST)
    String login(@ModelAttribute("user") User user, Model model, HttpSession session){
        try{
            if(Objects.equals(user.login(user.getUsername(), user.getPassword()), "No erreur")){
                session.setAttribute("status", "Ok");
                System.out.println("Ok");
                session.setAttribute("user", user.getUser(user.getUsername(), user.getPassword()));
                if((String)session.getAttribute("referer")==null){
                    return "redirect:home";
                }
                else{
                    return "redirect:"+(String)session.getAttribute("referer");
                }

            }
            else{
                model.addAttribute("error", user.login(user.getUsername(), user.getPassword()));
                return"login";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", "Une erreur s'est produite, veuillez réessayer plus tard !");
            return"login";
        }

    }
    @RequestMapping(value="/register", method= RequestMethod.POST)
    String register(@ModelAttribute("user") User user, Model model){
        try{
            if(user.register(user)){
                model.addAttribute("success", "Inscription réussie !");
                return "login";
            }
            else{
                model.addAttribute("error", "Une erreur s'est produite, veuillez réessayer plus tard !");
                return"login";
            }
        }
        catch(Exception e){
            System.out.println("Erreur");
        }
        finally{
            return "login";
        }

    }


}
