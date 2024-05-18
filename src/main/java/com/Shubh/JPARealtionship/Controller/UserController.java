package com.Shubh.JPARealtionship.Controller;


import com.Shubh.JPARealtionship.Entity.User;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.UserRequest;
import com.Shubh.JPARealtionship.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Validated
@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> addUser( @Valid @RequestBody UserRequest userRequest)
    {
        return ResponseEntity.of(Optional.ofNullable(userService.addUser(userRequest)));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id)
    {
        return ResponseEntity.of(Optional.ofNullable(userService.deleteUserById(id)));

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest)
    {
        return  ResponseEntity.of(Optional.ofNullable(userService.updateUser(id, userRequest)));

    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {

        return ResponseEntity.of(Optional.ofNullable(userService.getUserById(id)));
    }





}
