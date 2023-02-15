package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Landing {

    @Autowired
    private SongRepository songRepository;

    @RequestMapping("/")
    public String home(Model model){

        return "landing.html";
    }
/*
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logOut(){
        return "logout";
    }

    @RequestMapping("/newUserLogin")
    public String newUserLogin(Model model){
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin", userLogin);
        return "newUserLogin";
    }

    @PostMapping("/saveUserLogin")
    public String saveUser(@ModelAttribute("userLogin") UserLogin userLogin){
        //Save userLogin to DB
        userLoginService.saveUserLogin(userLogin);
        return "redirect:/";
    }
    */
}
