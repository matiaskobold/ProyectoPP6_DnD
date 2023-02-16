package com.matiaskobold.proyectopp6.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//With @ControllerAdvice, we can create a global handler class with multiple @ExceptionHandler methods to handle exceptions globally across the application.
//  ResponseEntityExceptionHandler This base class provides many methods that we can override to customize the way how we want to handle specific exceptions.
//  The provided methods return an instance of ResponseEntity<T>, which means that we can create and generate HTTP responses with the details (body, headers, status code) we want.

    @ControllerAdvice
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
    {

        //funciona perfecto, cuando no encuentra el id de alguien
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFoundException(
                ResourceNotFoundException ex) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getMessage());

            ApiError err = new ApiError(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Resource Not Found" ,
                    details);

            return ResponseEntityBuilder.build(err);
        }

        //Funciona perfecto -> En mi JSON mando un atributo int en forma de un string
        protected ResponseEntity<Object> handleHttpMessageNotReadable(
                HttpMessageNotReadableException ex,
                HttpHeaders headers,
                HttpStatus status,
                WebRequest request) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getMessage());

            ApiError err = new ApiError(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Malformed JSON request" ,
                    details);

            return ResponseEntityBuilder.build(err);
        }



        //funciona perfecto -> Cuando int edad es menor que 0, es una constraint que psue.
        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<?> ConstraintValidationException(
                Exception ex,
                WebRequest request) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getMessage());

            ApiError err = new ApiError(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Constraint Violations",
                    details);

            return ResponseEntityBuilder.build(err);
        }


        //funciona bien -> Cuando me falta un parameter o esta mal escrito
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<?> DataIntegrityViolationException(
                Exception ex,
                WebRequest request) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getMessage());

            ApiError err = new ApiError(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Missing parameter",
                    details);

            return ResponseEntityBuilder.build(err);
        }

        //este funciona perfecto -> cuando mando un txt en vez de un json
        @Override
        protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
                HttpMediaTypeNotSupportedException ex,
                HttpHeaders headers,
                HttpStatus status,
                WebRequest request) {

            List<String> details = new ArrayList<String>();
            StringBuilder builder = new StringBuilder();
            builder.append(ex.getContentType());
            builder.append(" media type is not supported. Supported media types are ");
            ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

            details.add(builder.toString());

            ApiError err = new ApiError(
                    LocalDateTime.now(),
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    "Unsupported Media Type" ,
                    details);

            return ResponseEntityBuilder.build(err);
        }

        //general, funciona perfecto, para cualquier excepcion que me haya comido
        @ExceptionHandler({ Exception.class })
        public ResponseEntity<Object> handleAll(
                Exception ex,
                WebRequest request) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getLocalizedMessage());

            ApiError err = new ApiError(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Error occurred" ,
                    details);

            return ResponseEntityBuilder.build(err);
        }


    }



