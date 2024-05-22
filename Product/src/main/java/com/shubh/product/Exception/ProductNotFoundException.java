package com.shubh.product.Exception;

public class ProductNotFoundException extends  RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
