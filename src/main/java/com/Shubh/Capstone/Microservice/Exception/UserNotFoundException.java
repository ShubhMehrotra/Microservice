package com.Shubh.Capstone.Microservice.Exception;

public class UserNotFoundException extends  RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
