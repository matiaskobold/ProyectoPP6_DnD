package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.repository.ClanRepository;
import com.matiaskobold.proyectopp6.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Songs {

    @Autowired
    ClanRepository clanRepository;
    @Autowired
    SongRepository songRepository;

    @RequestMapping("/songsTable")
    public String viewSongsTable(Model model){
        model.addAttribute("listSongs", songRepository.findAll());
        return "songsTable.html";
    }
}
