package com.shubh.customer.Exception;

public class LineItemNotFoundException extends RuntimeException{
    public LineItemNotFoundException(String message) {
        super(message);
    }
}
