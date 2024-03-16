package com.Shubh.Capstone.Microservice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class AddressResponse {
    private String message;
    private HttpStatus status;
}
