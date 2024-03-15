package com.Shubh.Capstone.Microservice.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data

public class UserResponse {
    private HttpStatus status;
    private String message;

    public UserResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
