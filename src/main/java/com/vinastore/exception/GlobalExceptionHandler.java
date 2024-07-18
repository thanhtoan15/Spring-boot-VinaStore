package com.vinastore.exception;

import com.vinastore.utils.ResponseBodyServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handlerRuntimeException(RuntimeException re){
        ResponseBodyServer bodyServer =ResponseBodyServer.builder().statusCode(500).message(re.getMessage()).build();
        return ResponseEntity.status(500).body(bodyServer);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handlerIllegalArgumentException(IllegalArgumentException iae){
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message(iae.getMessage()).build();
        return ResponseEntity.status(500).body(bodyServer);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidate(MethodArgumentNotValidException manve){
        ResponseBodyServer bodyServer = ResponseBodyServer.builder().statusCode(500).message(manve.getFieldError().getDefaultMessage()).build();
        return ResponseEntity.status(500).body(bodyServer);
    }

}
