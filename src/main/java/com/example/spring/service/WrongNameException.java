package com.example.spring.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongNameException extends RuntimeException {
    public WrongNameException() {
        super();
    }

    public WrongNameException(String message) {
        super(message);
    }
}
