package com.shubh.customer.Service;

import com.shubh.customer.Entity.Address;
import com.shubh.customer.Entity.User;
import com.shubh.customer.Exception.UserNotFoundException;
import com.shubh.customer.Payload.AddressRequest;
import com.shubh.customer.Payload.ApiResponse;
import com.shubh.customer.Payload.UserRequest;
import com.shubh.customer.Repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    @Override
    public Long addUser(UserRequest userRequest) {
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
                    User savedUser = userRepo.save(user);

                    try {
                        kafkaTemplate.send("user-created", savedUser.getUserId());
                    } catch (Exception e) {
                        System.err.println("Kafka Event Publishing Failed: " + e.getMessage());
                    }

                    return savedUser.getUserId();
                })
                .orElseThrow(() -> new RuntimeException("User creation failed"));
    }

    @Override
    public ApiResponse deleteUserById(Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK, "User deleted successfully", id);
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found in the system"));
    }

    @Override
    public ApiResponse updateUser(Long id, @Valid UserRequest userRequest) {
        return userRepo.findById(id)
                .map(existingUser -> {
                    BeanUtils.copyProperties(userRequest, existingUser, "userId");
                    userRepo.save(existingUser);
                    return new ApiResponse(HttpStatus.OK, "User updated successfully", existingUser.getUserId());
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found in the system"));
    }

    @Override
    public UserRequest getUserById(Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    UserRequest response = new UserRequest();
                    BeanUtils.copyProperties(user, response);
                    return response;
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found in the system"));
    }
}
