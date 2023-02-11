package com.matiaskobold.proyectopp6.exception;

public class HomeNotFoundException extends RuntimeException {
    public HomeNotFoundException(Long id){
        super("Could not find home "+id);
    }
}
