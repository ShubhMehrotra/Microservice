package com.Shubh.Capstone.Microservice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {
    private String message;
    private String status;
}
