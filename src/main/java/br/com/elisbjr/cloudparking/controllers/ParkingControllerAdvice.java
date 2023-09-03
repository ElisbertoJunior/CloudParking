package br.com.elisbjr.cloudparking.controllers;

import br.com.elisbjr.cloudparking.exception.FindNullException;
import br.com.elisbjr.cloudparking.exception.ParkingNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ParkingControllerAdvice {

    @ExceptionHandler(ParkingNullException.class)
    public ResponseEntity<Object> catchNullError() {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Verifique os campos do parking");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }

    @ExceptionHandler(FindNullException.class)
    public ResponseEntity<Object> findNullException() {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Item nao localizado ou inexistente.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }

}
