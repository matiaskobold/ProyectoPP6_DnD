package com.matiaskobold.proyectopp6.service;

import com.matiaskobold.proyectopp6.exception.CharacterNotFoundException;
import com.matiaskobold.proyectopp6.exception.HomeNotFoundException;
import com.matiaskobold.proyectopp6.model.Character;
import com.matiaskobold.proyectopp6.model.Home;
import com.matiaskobold.proyectopp6.repository.CharacterRepository;
import com.matiaskobold.proyectopp6.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HomeRepository homeRepository;

    public Character save(Character character) {
       /* Home home = character.getHome();
        home.setCharacter(character); */
        return characterRepository.save(character);
    }

    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    public Character findOneCharacter(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public String deleteCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
            return "Character does not exist";
        } else {
            characterRepository.deleteById(id);
            return "Character Deleted";
        }
    }

    public Character saveOrUpdateCharacter(Character character) {
        return characterRepository.findById(
                        character.getId())
                .map(newCharacter -> {
                    newCharacter.setAge(character.getAge());
                    newCharacter.setPlayer_discord(character.getPlayer_discord());
                    newCharacter.setRace(character.getRace());
                    newCharacter.setPlayer_username(character.getPlayer_username());
                    newCharacter.setName(character.getName());
                    newCharacter.setHome(character.getHome());
                    return characterRepository.save(newCharacter);
                })
                .orElseGet(() -> {
                    character.setId(character.getId());
                    return characterRepository.save(character);
                });
    }

    public Character AddHomeOfCharacter(Long id, Home home) {

        Character character = characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
        character.setHome(home);
        return characterRepository.save(character);
    }

    public String deleteHomeOfACharacter(Long id) {

            Character character = characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
            character.setHome(null);
            characterRepository.save(character);
            return "Home deleted";
    }

    public Character addExistingHomeToCharacter(Long idC, Long idH) {
        Character character = characterRepository.findById(idC).orElseThrow(() -> new CharacterNotFoundException(idC));
        Home home = homeRepository.findById(idH).orElseThrow(()->new HomeNotFoundException(idH));
        character.setHome(home);
        return characterRepository.save(character);
    }
}
