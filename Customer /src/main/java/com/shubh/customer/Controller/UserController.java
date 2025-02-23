package com.shubh.customer.Controller;

import com.shubh.customer.Payload.ApiResponse;
import com.shubh.customer.Payload.UserRequest;
import com.shubh.customer.Payload.UserRequest;
import com.shubh.customer.Service.UserServiceImpl;
import com.shubh.customer.dto.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        Long userId = userService.addUser(userRequest);
        ApiResponse response = new ApiResponse(HttpStatus.CREATED, "User created successfully", userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        ApiResponse response = userService.deleteUserById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        ApiResponse response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<CustomerDto> getUserById(@PathVariable Long id) {
        CustomerDto userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }
}
