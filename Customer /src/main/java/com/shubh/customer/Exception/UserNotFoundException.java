package com.shubh.customer.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message)
    {

        super(message);
    }
}
