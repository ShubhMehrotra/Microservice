package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Exception.UserNotFoundException;
import com.Shubh.Capstone.Microservice.Payload.AddressRequest;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
   UserRepo userRepo;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User use=new User();
        BeanUtils.copyProperties(userRequest,use);
        List<Address> addresses = new ArrayList<>();
        if (userRequest.getUser_Email() != null) {
            for (AddressRequest addressRequest : userRequest.getAddressRequests()) {
                Address address = new Address();
                BeanUtils.copyProperties(addressRequest,address);
                addresses.add(address);
            }
        }
        use.setAddress(addresses);
        userRepo.save(use);
        return new UserResponse(HttpStatus.CREATED,"User Created Successfully");
    }

    @Override
    public UserResponse deleteUserById(Long id) {
        Optional<User> user=userRepo.findById(id);
        if(user.isEmpty())
            return new UserResponse(HttpStatus.NOT_FOUND, "User to be deleted not found in System");
        userRepo.deleteById(id);
        return  new UserResponse(HttpStatus.OK,"User Deleted");

    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND,"User to be UPDATED with this ID not found in System");
        }
        User user = userOptional.get();
        BeanUtils.copyProperties(userRequest,user);
        if (userRequest.getAddressRequests() != null) {
            List<Address> addresses = new ArrayList<>();
            for (AddressRequest addressRequest : userRequest.getAddressRequests()) {
                Address address = new Address();
                BeanUtils.copyProperties(addressRequest, address);
                addresses.add(address);
            }
            user.setAddress(addresses);
        }
        User updatedUser = userRepo.save(user);
        return new UserResponse(HttpStatus.OK, "User with ID " + id + " updated successfully");
    }







    @Override
    public List<User> searchUsers() {
        List<User> user=userRepo.findAll();
        if(user.isEmpty())
            throw new UserNotFoundException(HttpStatus.NOT_FOUND,"No Users found in System");
        return  user;
    }

    @Override
    public User searchById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND,"User not found for this id-" + id));
    }





}



