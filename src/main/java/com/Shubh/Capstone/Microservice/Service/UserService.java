package com.Shubh.Capstone.Microservice.Service;
import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;

import java.util.List;


public interface UserService {

    public UserResponse addUser(UserRequest userRequest);
    public UserResponse deleteUserById (Long id);
    public UserResponse updateUser(Long id, UserRequest userRequest);
    public List<User> searchUsers();
    public User searchById(Long id);



}
