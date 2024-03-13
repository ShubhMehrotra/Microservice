package com.Shubh.Capstone.Microservice.Controller;


import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Index")
public class Controller {
   @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    ResponseEntity<User> addUser(@RequestBody @Valid User user)
    {
        var userResponse=userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }








}
