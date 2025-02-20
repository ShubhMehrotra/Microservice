package com.shubh.customer.Service;
import com.shubh.customer.Entity.User;
import com.shubh.customer.Payload.ApiResponse;
import com.shubh.customer.Payload.UserRequest;


public interface UserService {

    public Long addUser(UserRequest userRequest);
    public ApiResponse deleteUserById(Long id);
    public ApiResponse updateUser(Long id, UserRequest userRequest);
    public UserRequest getUserById(Long id);

}
