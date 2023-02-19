package com.matiaskobold.proyectopp6.controller;


import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestHomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/homes")
    public ResponseEntity<?> listAllHomes() {
        return homeService.findAll();                                  //en MAVEN por SpringBoot ya está puesto el Jackson, que es el que te convierte a JSON
    }
    @PostMapping("/homes")
    public ResponseEntity<Home> newHome(@RequestBody @Valid Home newHome){
        return homeService.saveHome(newHome);
    }
/*
    @GetMapping("/homes")
    public List<Home> listAllHomes() {
        return homeService.findAll();
    }
*/
    @PutMapping("/homes/{id}")
    public ResponseEntity<Home> updateHome(@RequestBody @Valid Home newHome, @PathVariable("id") Long id){
        return homeService.updateHome(newHome, id);
    }
}
