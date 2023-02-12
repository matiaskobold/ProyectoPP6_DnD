package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Landing {

    @Autowired
    private SongRepository songRepository;

    @RequestMapping("/")
    public String home(Model model){

        return "landing.html";
    }
}
