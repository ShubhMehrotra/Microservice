package com.Shubh.Shipping.Shipping.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCartResponse {
    private CustomerDto customer;
    private CartDto cart;
}
