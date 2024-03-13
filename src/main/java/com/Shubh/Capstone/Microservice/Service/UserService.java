package com.Shubh.Capstone.Microservice.Service;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;

import java.util.List;


public interface UserService {

    public User addUser(User user);
    public UserResponse deleteUser (Long id);
    public UserResponse updateUser(Long id,User user);
    public List<User> searchUser();
    public User searchById(Long id);

}
