package com.matiaskobold.proyectopp6.exception;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(Long id) {
        super("Could not find character "+id);
    }
}
