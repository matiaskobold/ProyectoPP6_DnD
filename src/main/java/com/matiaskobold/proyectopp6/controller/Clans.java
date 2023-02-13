package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class Clans {
    @Autowired
    ClanRepository clanRepository;

    @RequestMapping("/clansTable")
    public String viewClansTable(Model model){
        model.addAttribute("listClans", clanRepository.findAll());
        return "clansTable.html";
    }

    @RequestMapping("/showNewClanForm")
    public String showNewClanForm(Model model){
        //Create model atribute to bind next form data (in new_clan.html)
        Clan clan = new Clan();
        model.addAttribute("clan", clan);
        return "new_clan.html";
    }

    @RequestMapping("/saveClan")
    public String saveClan(@ModelAttribute("clan") Clan clan){
        //Save clan to DB
        clanRepository.save(clan);
        return "redirect:/clansTable";
    }

    @RequestMapping("/showClanFormForUpdate/{id}")
    public String showClanFormForUpdate(@PathVariable(value="id") Long id, Model model){
        //get Clan from the repo
        Optional<Clan> clan= clanRepository.findById(id);
        //set Clan  as a model attribute to pre-populate the next form data (in update_user)
        model.addAttribute("clan", clan);
        return "update_clan.html";
    }

    @RequestMapping("/deleteClan/{id}")
    public String deleteClan(@PathVariable(value="id") Long id, Model model){
        //call delete clan method from repo
        clanRepository.deleteById(id);
        return "redirect:/clansTable";
    }
}
