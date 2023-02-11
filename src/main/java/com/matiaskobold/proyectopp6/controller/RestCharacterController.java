package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.repository.CharacterRepository;
import com.matiaskobold.proyectopp6.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Me dice que cada return debe ser llevado a un responseBody en vez
public class RestCharacterController {

    //el characterRepository no lo deberia usar, deberia usar el service.
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterService characterService;

    @GetMapping("/characters")
    public List<Character> listAllCharacters() {
        return characterService.findAll();                                  //en MAVEN por SpringBoot ya está puesto el Jackson, que es el que te convierte a JSON
    }

    @GetMapping("/character/{id}")
    public Character findOneCharacter(@PathVariable("id") Long id) {

        return characterService.findOneCharacter(id);
    }


    @PostMapping("/character")
    public Character newCharacter(@RequestBody Character newCharacter) {    //RequestBody me sirve para agarrar el body si lo mando como raw data en json.

        return characterService.save(newCharacter);
    }

    @PutMapping("/character")
    public Character saveOrUpdateCharacter(@RequestBody Character newCharacter) {

        return characterService.saveOrUpdateCharacter(newCharacter);
    }

    @DeleteMapping("/character/{id}")
    public String deleteCharacter(@PathVariable("id") Long id) {
        return characterService.deleteCharacter(id);

    }

    //PUT localhost:8080/character/1/home
    @PostMapping("/character/{id}/home")
    public Character addHomeOfCharacter(@PathVariable("id") Long id, @RequestBody Home home) {

        return characterService.AddHomeOfCharacter(id, home);
    }

    @PutMapping("/character/{idC}/home/{idH}")
    public Character addExistingHomeToCharacter(@PathVariable("idC") Long idC, @PathVariable("idH") Long idH) {

        return characterService.addExistingHomeToCharacter(idC, idH);
    }


    @DeleteMapping("/character/{id}/home")
    public String deleteHomeOfCharacter(@PathVariable("id") Long id) {
        return characterService.deleteHomeOfACharacter(id);
    }



}
