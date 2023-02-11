package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.service.ClanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestClanController {

    @Autowired
    private ClanService clanService;

    @GetMapping("/clans")
    public ResponseEntity<?> getAllClans(){
        return clanService.getAllClans();
    }

    @GetMapping("/clans/{id}")
    public ResponseEntity<Clan> getClanById(@PathVariable("id") Long id) {
        return clanService.getClanById(id);
    }

    @PostMapping("/clans")
    public ResponseEntity<Clan> createClan(@RequestBody Clan clan)  {
       return clanService.createClan(clan);

    }
    @PutMapping("/clans/{id}")
    public ResponseEntity<Clan> updateClan(@PathVariable(value="id") Long id, @RequestBody Clan clan){
        return clanService.updateClan(id, clan);

    }

    @DeleteMapping("/clans/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
        return clanService.deleteById(id);
    }

    }
