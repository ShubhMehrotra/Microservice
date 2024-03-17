package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Payload.AddressRequest;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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



