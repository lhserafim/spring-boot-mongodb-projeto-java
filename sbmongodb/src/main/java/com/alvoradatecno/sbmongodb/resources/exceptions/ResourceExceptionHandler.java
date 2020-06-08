package com.alvoradatecno.sbmongodb.resources.exceptions;

import com.alvoradatecno.sbmongodb.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice // Para interceptar as possíveis exceções p/ que este obj execute um tratamento
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class) // Interceptar a req que deu exceção
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        String error = "Object not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
