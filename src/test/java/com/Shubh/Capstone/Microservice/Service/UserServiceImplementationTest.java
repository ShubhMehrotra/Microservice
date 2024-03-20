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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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


    @Test
    void addUser() {

        List<AddressRequest> addressRequests=new ArrayList<>();
        AddressRequest addressRequest=new AddressRequest(
                "1","Street-1","City","11111"
        );
        AddressRequest addressRequest1=new AddressRequest(
                "2","Street-2","City","22222"
        );
        addressRequests.add(addressRequest);
        addressRequests.add(addressRequest1);

        UserRequest userRequest=new UserRequest(
                "Shubh","Test@user.com",addressRequests
        );
        userServiceImplementation.addUser(userRequest);

        verify(userRepo, times(1)).save(any(User.class));
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepo).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(userRequest.getUser_Name(), capturedUser.getUser_Name());
        assertEquals(userRequest.getUser_Email(), capturedUser.getUser_Email());
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
        UserRequest updatedUserRequest = new UserRequest();
        List<AddressRequest> updatedAddressRequests = new ArrayList<>();
        updatedUserRequest.setUser_Name("Updated Name");
        updatedUserRequest.setUser_Email("updated@example.com");

        AddressRequest addressRequest=new AddressRequest(
                "1","Street-1","City","11111"
        );
        AddressRequest addressRequest1=new AddressRequest(
                "2","Street-2","City","22222"
        );

        updatedAddressRequests.add(addressRequest);
        updatedAddressRequests.add(addressRequest1);
        updatedUserRequest.setAddressRequests(updatedAddressRequests);

        User existingUser = new User();
        existingUser.setUser_Id(1L);
        when(userRepo.findById(1L)).thenReturn(Optional.of(existingUser));

        UserResponse userResponse = userServiceImplementation.updateUser(1L, updatedUserRequest);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepo, times(1)).findById(1L);
        verify(userRepo, times(1)).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(updatedUserRequest.getUser_Name(), capturedUser.getUser_Name());
        assertEquals(updatedUserRequest.getUser_Email(), capturedUser.getUser_Email());

        assertEquals(HttpStatus.OK, userResponse.getStatus());
        assertEquals("User with ID 1 updated successfully", userResponse.getMessage());
    }




    @Test
    void searchUsers() {
        List<User> users = new ArrayList<>();
        List<Address> addresses=new ArrayList<>();
        Address address=new Address(1L,
                "1","Street-1","City","11111"
        );
        Address  address1=new Address(2L,
                "2","Street-2","City","22222"
        );
        addresses.add(address);addresses.add(address1);


        User user=new User();
        user.setUser_Name("Shubh");
        user.setUser_Email("User@Test.com");
        user.setAddress(addresses);
        users.add(user);




        when(userRepo.findAll()).thenReturn(users);


        List<User> foundUsers = userServiceImplementation.searchUsers();

        assertEquals(users.get(0).getUser_Name(),foundUsers.get(0).getUser_Name());
        assertEquals(users.get(0).getUser_Email(),foundUsers.get(0).getUser_Email());
        assertEquals(users.size(), foundUsers.size());
        assertEquals(users, foundUsers);
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



