package com.matiaskobold.proyectopp6.controller;


import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestHomeController {

    @Autowired
    HomeService homeService;

    @PostMapping("/home")
    public Home newHome(@RequestBody Home newHome){
        return homeService.saveHome(newHome);
    }
/*
    @GetMapping("/homes")
    public List<Home> listAllHomes() {
        return homeService.findAll();
    }
*/
    @PutMapping("/home")
    Home saveOrUpdateHome(@RequestBody Home newHome) {
        return homeService.saveOrUpdateHome(newHome);
    }
}
