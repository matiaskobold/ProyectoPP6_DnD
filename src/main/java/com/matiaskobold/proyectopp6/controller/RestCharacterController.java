package com.matiaskobold.proyectopp6.controller;

import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.repository.CharacterRepository;
import com.matiaskobold.proyectopp6.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //Me dice que cada return debe ser llevado a un responseBody en vez

public class RestCharacterController {

    //el characterRepository no lo deberia usar, deberia usar el service.
    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterService characterService;

    @GetMapping("/characters")
    public ResponseEntity<?> listAllCharacters() {
        return characterService.findAll();                                  //en MAVEN por SpringBoot ya está puesto el Jackson, que es el que te convierte a JSON
    }

    @GetMapping("/character/{id}")
    public ResponseEntity<Character> findOneCharacter(@PathVariable("id") Long id) {

        return characterService.findOneCharacter(id);
    }


    @PostMapping("/character")
    public ResponseEntity<?> newCharacter(@RequestBody Character newCharacter) {    //RequestBody me sirve para agarrar el body si lo mando como raw data en json.

        return characterService.save(newCharacter);
    }

    @PutMapping("/character/{id}")
    public ResponseEntity<Character> updateCharacter(@RequestBody @Valid Character newCharacter, @PathVariable("id") Long id) {

        return characterService.updateCharacter(newCharacter, id);
    }

    @DeleteMapping("/character/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable("id") Long id) {
        return characterService.deleteCharacter(id);

    }

    //PUT localhost:8080/character/1/home
    @PostMapping("/character/{id}/home")
    public ResponseEntity<Character> addHomeOfCharacter(@PathVariable("id") Long id, @Valid @RequestBody Home home) {

        return characterService.AddHomeOfCharacter(id, home);
    }

    @PutMapping("/character/{idC}/home/{idH}")
    public ResponseEntity<Character> addExistingHomeToCharacter(@PathVariable("idC") Long idC, @PathVariable("idH") Long idH) {

        return characterService.addExistingHomeToCharacter(idC, idH);
    }


    @DeleteMapping("/character/{id}/home")
    public ResponseEntity<String> deleteHomeOfCharacter(@PathVariable("id") Long id) {
        return characterService.deleteHomeOfACharacter(id);
    }



}
