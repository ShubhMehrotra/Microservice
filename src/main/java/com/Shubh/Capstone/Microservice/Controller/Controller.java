package com.Shubh.Capstone.Microservice.Controller;

import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Exception.UserNotFoundException;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Index")
public class Controller {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody  UserRequest userRequest) {
        Optional<UserResponse>userResponse= Optional.ofNullable(userService.addUser(userRequest));
        return userResponse.map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(response)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new UserResponse(HttpStatus.BAD_REQUEST,"User Failed to get ADDED")));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable  Long id){
        Optional<UserResponse> userResponse = Optional.ofNullable(userService.deleteUserById(id));
        return userResponse.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body(new UserResponse(HttpStatus.NOT_FOUND, "User not found or failed to delete")));
}

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.updateUser(id, userRequest);
            return ResponseEntity.ok().body(userResponse);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"));
        }
    }



    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> searchUsers() {
        List<User> users = userService.searchUsers();
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // or you can return an empty list if preferred
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<User> searchUser(@PathVariable Long id){

        Optional<User > optionalUser= Optional.ofNullable(userService.searchById(id));
        User user=optionalUser
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found for this id-" + id));
        return ResponseEntity.ok(user);


    }


    }






