package com.Shubh.Capstone.Microservice.Controller;


import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Index")
public class Controller {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest) {
        Optional<UserResponse>userResponse= Optional.ofNullable(userService.addUser(userRequest));
        return userResponse.map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(response)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new UserResponse(HttpStatus.BAD_REQUEST,"User Failed to get ADDED")));
}
}