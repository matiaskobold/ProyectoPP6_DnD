package com.matiaskobold.proyectopp6.service;

import com.matiaskobold.proyectopp6.exception.ResourceNotFoundException;
import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.repository.CharacterRepository;
import com.matiaskobold.proyectopp6.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HomeRepository homeRepository;

    public ResponseEntity<?> save(Character character) {
       Character savedCharacter = characterRepository.save(character);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCharacter.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body("Character created at: "+location);
    }

    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    public ResponseEntity<Character> findOneCharacter(Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character with id "+id+" not found!"));
        return ResponseEntity.status(HttpStatus.FOUND).body(character);
    }

    public ResponseEntity<String> deleteCharacter(Long id) {

        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character with id "+id+" not found!"));
            characterRepository.deleteById(id);
            return ResponseEntity.ok().body("Character deleted!");
        }


    public ResponseEntity<Character> updateCharacter(Character character, Long id) {
        Character updCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character with id "+id+" not found!"));
        character.setId(updCharacter.getId());
        characterRepository.save(character);
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

    public ResponseEntity<Character> AddHomeOfCharacter(Long id, Home home) {

        Character character = characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character with ID "+id+" not found!"));
        character.setHome(home);
        characterRepository.save(character);
        return ResponseEntity.ok(character);
    }

    public ResponseEntity<String> deleteHomeOfACharacter(Long id) {

            Character character = characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character with ID "+id+" not found!"));
            character.setHome(null);
            characterRepository.save(character);
            return ResponseEntity.status(HttpStatus.OK).body("Home of character deleted!");
    }

    public ResponseEntity<Character> addExistingHomeToCharacter(Long idC, Long idH) {
        Character character = characterRepository.findById(idC).orElseThrow(() -> new ResourceNotFoundException("Character with ID "+idC+" not found!"));
        Home home = homeRepository.findById(idH).orElseThrow(()->new ResourceNotFoundException("Home with ID "+idH+" not found!"));
        character.setHome(home);
        characterRepository.save(character);
        return ResponseEntity.ok().body(character);
    }
}
