package com.matiaskobold.proyectopp6.model.assembler;

import com.matiaskobold.proyectopp6.controller.RestCharacterController;
import com.matiaskobold.proyectopp6.model.Character;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CharacterAssembler implements RepresentationModelAssembler<Character, EntityModel<Character>> {
    @Override
    public EntityModel<Character> toModel(Character character) {
        return EntityModel.of(character,
                linkTo(methodOn(RestCharacterController.class).findOneCharacter(character.getId())).withSelfRel(),
                linkTo(methodOn(RestCharacterController.class).listAllCharacters()).withSelfRel());


    }
}
