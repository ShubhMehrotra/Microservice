package com.shubh.customer.Service;
import com.shubh.customer.Entity.User;
import com.shubh.customer.Payload.ApiResponse;
import com.shubh.customer.Payload.UserRequest;


public interface UserService {

    public ApiResponse addUser(UserRequest userRequest);
    public ApiResponse deleteUserById(Long id);
    public ApiResponse updateUser(Long id, UserRequest userRequest);
    public User getUserById(Long id);

}
