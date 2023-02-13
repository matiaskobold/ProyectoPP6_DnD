package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Song;
import com.matiaskobold.proyectopp6.repository.ClanRepository;
import com.matiaskobold.proyectopp6.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @RequestMapping("/deleteSong/{id}")
    public String deleteUser(@PathVariable(value="id") Long id, Model model){

        songRepository.deleteById(id);
        return "redirect:/songsTable";
    }

    @RequestMapping("/showNewSongForm")
    public String showNewUserForm(Model model){
        //Create model attribute to bind next form data (in new_user.html)
        if (clanRepository.findAll().isEmpty()){
            //String errorMessage = "No se han creado clanes, cree un clan primero para luego agregar un usuario.";
            //model.addAttribute("errorMessage", errorMessage);
            return "redirect:/showNewClanForm";
        }
        else {
            Song song= new Song();
            model.addAttribute("listClans", clanRepository.findAll());
            model.addAttribute("song", song);
            return "new_song.html";
        }
    }

    @RequestMapping("/saveSong")
    public String saveSong(@ModelAttribute("song") Song song){

        songRepository.save(song);
        return "redirect:/songsTable";
    }

    @RequestMapping("/showSongFormForUpdate/{id}")
    public String showSongFormUpdate(@PathVariable(value="id") Long id, Model model){
        Optional<Song> song = songRepository.findById(id);
        //set User as a model attribute to pre-populate the next form data (in update_user)
        model.addAttribute("listClans", clanRepository.findAll());
        model.addAttribute("song", song);
        return "update_song.html";
    }
}
