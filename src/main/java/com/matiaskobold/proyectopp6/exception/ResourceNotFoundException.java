package com.matiaskobold.proyectopp6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Spring offers this annotation to bind a particular Exception to a specific HTTP response status.
//So, when Spring catches that particular Exception, it generates an HTTP response with the settings defined in @ResponseStatus.
@ResponseStatus(HttpStatus.NOT_FOUND)   //como lo anote así, devuelve httpstatus.not_found cuando triggerea este exception
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
