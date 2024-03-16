package com.Shubh.Capstone.Microservice.Exception;

import com.Shubh.Capstone.Microservice.Payload.AddressResponse;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<AddressResponse> handleAddressNotFoundException(AddressNotFoundException addressNotFoundException)
    {
        AddressResponse addressResponse=new AddressResponse("NOT FOUND", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(addressResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException)
    {
        UserResponse userResponse=new UserResponse(HttpStatus.NOT_FOUND,"USER with this ID not found in System");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);

    }
}
