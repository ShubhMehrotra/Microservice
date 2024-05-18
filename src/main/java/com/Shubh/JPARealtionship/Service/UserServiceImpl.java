package com.Shubh.JPARealtionship.Service;

import com.Shubh.JPARealtionship.Entity.Address;
import com.Shubh.JPARealtionship.Entity.User;
import com.Shubh.JPARealtionship.Exception.UserNotFoundException;
import com.Shubh.JPARealtionship.Payload.AddressRequest;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.UserRequest;
import com.Shubh.JPARealtionship.Repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public ApiResponse addUser(UserRequest userRequest) {

            return Optional.ofNullable(userRequest)
                    .map(request -> {
                        User user = new User();
                        BeanUtils.copyProperties(userRequest, user);
                        List<Address> addresses = new ArrayList<>();
                        if (userRequest.getAddressRequests() != null) {
                            for (AddressRequest addressRequest : userRequest.getAddressRequests()) {
                                Address address = new Address();
                                BeanUtils.copyProperties(addressRequest, address);
                                addresses.add(address);
                            }
                        }
                        user.setAddressList(addresses);
                        userRepo.save(user);
                        return new ApiResponse(HttpStatus.CREATED, "User Created Successfully");
                    })
                    .orElse(new ApiResponse(HttpStatus.BAD_REQUEST, "User request is null"));
        }




    @Override
    public ApiResponse deleteUserById(Long id) {
        return userRepo.findById(id)
                .map(user->{
                    userRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK,"User deleted Successfully");
                })
                .orElse(new ApiResponse(HttpStatus.NOT_FOUND,"User with this Id not found in the system"));

    }

    @Override
    public ApiResponse updateUser(Long id, @Valid UserRequest userRequest) {
        return Optional.ofNullable(userRequest)
                .map(user->{
                    User user1=new User();
                    BeanUtils.copyProperties(userRequest,user1);
                    userRepo.save(user1);
                    return new ApiResponse(HttpStatus.CREATED,"User updated Successfully");
                }).orElse(new ApiResponse(HttpStatus.NOT_FOUND,"User with this Id not found in the system"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + "Not exist in the system"));
    }
}
