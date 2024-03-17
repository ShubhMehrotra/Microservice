package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Payload.AddressRequest;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    @Mock
    private UserRepo userRepo;

    private UserServiceImplementation userServiceImplementation;

    @BeforeEach
    void setUp() {
        this.userServiceImplementation=new UserServiceImplementation(this.userRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addUser() {

        UserRequest userRequest = new UserRequest();
        userRequest.setUser_Name("Test User");
        userRequest.setUser_Email("test@example.com");

        List<AddressRequest> addresses = new ArrayList<>();
        AddressRequest address1 = new AddressRequest();
        address1.setCity("City1");
        address1.setPin_Code("221005");
        address1.setDoor_No("DoorNo-1");
        address1.setStreet_Name("Street 1");
        addresses.add(address1);

        AddressRequest address2 = new AddressRequest();
        address2.setCity("City2");
        address2.setStreet_Name("Street2");
        address2.setDoor_No("DoorNo-2");
        address2.setPin_Code("221005");
        addresses.add(address2);

        userRequest.setAddressRequests(addresses);
        when(userRepo.save(any(User.class))).thenReturn(new User());
        UserResponse response =userServiceImplementation.addUser(userRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals("User Created Successfully", response.getMessage());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void deleteUserById() {

        Long userId = 1L;
        User user = new User();
        user.setUser_Id(userId);
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        UserResponse response = userServiceImplementation.deleteUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("User Deleted", response.getMessage());
        verify(userRepo, times(1)).deleteById(userId);
    }


    @Test
    void updateUser() {
    }

    @Test
    void searchUsers() {


    }

    @Test
    void searchById() {
            Long userId = 1L;
            User expectedUser = new User();
            expectedUser.setUser_Id(userId);
            expectedUser.setUser_Name("TestUser");
            when(userRepo.findById(userId)).thenReturn(Optional.of(expectedUser));
            User foundUser = userServiceImplementation.searchById(userId);
            assertNotNull(foundUser);
            assertEquals(userId, foundUser.getUser_Id());
            assertEquals("TestUser", foundUser.getUser_Name());
        }

    }
