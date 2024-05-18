package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Entity.User;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.UserRequest;

public interface UserService {

    public ApiResponse addUser(UserRequest userRequest);
    public ApiResponse deleteUserById(Long id);
    public ApiResponse updateUser(Long id, UserRequest userRequest);
    public User getUserById(Long id);

}
