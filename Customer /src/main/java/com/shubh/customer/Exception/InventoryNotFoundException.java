package com.shubh.customer.Exception;

public class InventoryNotFoundException extends RuntimeException{

    public InventoryNotFoundException(String message)
    {
        super(message);
    }
}
