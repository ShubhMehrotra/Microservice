package com.Shubh.Capstone.Microservice.Exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends  RuntimeException{

    public UserNotFoundException(HttpStatus status,String message) {
        super(message);
    }
}
