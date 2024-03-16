package com.Shubh.Capstone.Microservice.Exception;

import com.Shubh.Capstone.Microservice.Payload.AddressResponse;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<String,String> response=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((objectError -> {
            String fieldName=((FieldError)objectError).getField();
            String message=objectError.getDefaultMessage();
            response.put(fieldName,message);
        }));

        return new ResponseEntity<Map<String, String>>(response,HttpStatus.BAD_REQUEST);

    }



}
