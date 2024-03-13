package com.Shubh.Capstone.Microservice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private String status;
    private String message;
}
