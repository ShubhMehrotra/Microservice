package com.shubh.customer.Service;

import com.shubh.customer.Entity.Address;
import com.shubh.customer.Entity.User;
import com.shubh.customer.Exception.UserNotFoundException;
import com.shubh.customer.Payload.AddressRequest;
import com.shubh.customer.Payload.ApiResponse;
import com.shubh.customer.Payload.UserRequest;
import com.shubh.customer.Repository.UserRepo;
import com.shubh.customer.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

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

                    // Save the user and ensure ID is generated
                    User savedUser = userRepo.save(user);

                    if (savedUser == null || savedUser.getUserId() == null) {
                        throw new RuntimeException("User creation failed: ID not generated");
                    }

                    //  Send Kafka event safely
                    try {
                        kafkaTemplate.send("user-created", savedUser.getUserId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage()); // ✅ Use proper logging
                    }

                    return savedUser.getUserId();
                })
                .orElseThrow(() -> new RuntimeException("User creation failed: Invalid request"));
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
    public CustomerDto getUserById(Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    log.info("✅ Retrieved User from DB: {}", user);

                    CustomerDto response = new CustomerDto();

                    // ✅ Debugging log before setting ID
                    log.info("🔍 User ID from DB: {}", user.getUserId());

                    response.setId(user.getUserId()); // ✅ Explicitly setting ID

                    // ✅ Copy properties and debug the response
                    BeanUtils.copyProperties(user, response, "userId");
                    log.info("📌 Response before returning: {}", response);

                    if (user.getAddressList() != null) {
                        List<AddressRequest> addressRequests = new ArrayList<>();
                        for (Address address : user.getAddressList()) {
                            AddressRequest addressRequest = new AddressRequest();
                            BeanUtils.copyProperties(address, addressRequest);
                            addressRequests.add(addressRequest);
                        }
                        response.setAddressRequests(addressRequests);
                    } else {
                        response.setAddressRequests(new ArrayList<>());
                    }

                    log.info("🚀 Final Response Sent to Controller: {}", response);
                    return response;
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " not found in the system"));
    }



}
