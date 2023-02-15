package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.repository.SongRepository;
import com.matiaskobold.proyectopp6.security.User;
import com.matiaskobold.proyectopp6.security.UserRepository;
import com.matiaskobold.proyectopp6.security.UserService;
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

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(Model model){

        return "landing.html";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login.html";
    }

    @RequestMapping("/logout-success")
    public String logOutPage(){
        return "logout.html";
    }

    @RequestMapping("/newUser")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "newUser.html";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        //Save userLogin to DB
        userService.saveUser(user);
        return "redirect:/";
    }

}
