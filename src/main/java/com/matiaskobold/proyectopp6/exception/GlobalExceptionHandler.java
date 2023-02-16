package com.matiaskobold.proyectopp6.exception;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//With @ControllerAdvice, we can create a global handler class with multiple @ExceptionHandler methods to handle exceptions globally across the application.
//  ResponseEntityExceptionHandler This base class provides many methods that we can override to customize the way how we want to handle specific exceptions.
//  The provided methods return an instance of ResponseEntity<T>, which means that we can create and generate HTTP responses with the details (body, headers, status code) we want.

    @ControllerAdvice
    public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
    {
        // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
        @Override
        public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
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

            ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Invalid JSON" ,details);

            return ResponseEntityBuilder.build(err);

        }

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

        // handleHttpMessageNotReadable : triggers when the JSON is malformed
        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getMessage());

            ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Malformed JSON request" ,details);

            return ResponseEntityBuilder.build(err);
        }

        // handleMissingServletRequestParameter : triggers when there are missing parameters
        @Override
        protected ResponseEntity<Object> handleMissingServletRequestParameter(
                MissingServletRequestParameterException ex, HttpHeaders headers,
                HttpStatus status, WebRequest request) {

            List<String> details = new ArrayList<String>();
            details.add(ex.getParameterName() + " parameter is missing");

            ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Missing Parameters" ,details);

            return ResponseEntityBuilder.build(err);
        }

        // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                          WebRequest request) {
            List<String> details = new ArrayList<String>();
            details.add(ex.getMessage());

            ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Mismatch Type" ,details);

            return ResponseEntityBuilder.build(err);
        }


    }



