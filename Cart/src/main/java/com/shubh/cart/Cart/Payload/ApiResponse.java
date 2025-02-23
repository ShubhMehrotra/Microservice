package com.shubh.cart.Cart.Payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Ensures null values are not included in JSON
public class ApiResponse {
    private HttpStatus httpStatus;
    private String message;
}
