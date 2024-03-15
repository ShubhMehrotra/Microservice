package com.Shubh.Capstone.Microservice.Exception;

public class AddressNotFoundException extends  RuntimeException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}
