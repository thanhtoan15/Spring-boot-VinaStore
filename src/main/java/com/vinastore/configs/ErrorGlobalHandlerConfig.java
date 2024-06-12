package com.vinastore.configs;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import com.vinastore.utils.ResponseBodyServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorGlobalHandlerConfig {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerIllegalArgumentException(IllegalArgumentException iae){
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message("Parameters is not valid").payload(iae.getMessage()).build();
        return ResponseEntity.status(500).body(bodyServer);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException nfe){
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(404).message("Request not found").payload(nfe.getMessage()).build();
        return ResponseEntity.status(404).body(bodyServer);
    }
}
