package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Exception.UserNotFoundException;
import com.Shubh.Capstone.Microservice.Payload.UserRequest;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
   UserRepo userRepo;

    @Override
    public User addUser(UserRequest userRequest) {
        User use=new User();
        use.setUser_Name(userRequest.getUser_Name());
        use.setUser_Email(userRequest.getUser_Email());
        List<Address> addresses = new ArrayList<>();
        if (userRequest.getAddresses() != null) {
            for (Address addressRequest : userRequest.getAddresses()) {
                Address address = new Address();
                address.setDoor_No(addressRequest.getDoor_No());
                address.setStreet_Name(addressRequest.getStreet_Name());
                address.setCity(addressRequest.getCity());
                address.setPin_Code(addressRequest.getPin_Code());
                addresses.add(address);
            }
        }
        use.setAddress(addresses);

        return userRepo.save(use);
    }

    @Override
    public UserResponse deleteUserById(Long id) {
        Optional<User> user=userRepo.findById(id);
        if(user.isEmpty())
            return new UserResponse("FAILED", "User to be deleted not found in System");
        userRepo.deleteById(id);
        return  new UserResponse("SUCCESS","User Deleted");

    }



    @Override
    public UserResponse updateUser(Long id, User user) {
        Optional<User> use=userRepo.findById(id);
        if(use.isEmpty())
            return new UserResponse("FAILED", "User with this ID is not present in system");
        User updatedUser = userRepo.save(user);
        return new UserResponse("SUCCESS","User Updated-"+ updatedUser.getUser_Name());

    }

    @Override
    public List<User> searchUser() {
        List<User> user=userRepo.findAll();
        if(user.isEmpty())
            throw new UserNotFoundException("No Users found in System");
        return  user;
    }

    @Override
    public User searchById(Long id) {
        return userRepo
                .findById(id)
                .orElseThrow(()->  new UserNotFoundException("User not found for this id-" + id));
    }

//    public void addAddressToUser(Long userId, Address address) {
//        Optional<User> userOptional = userRepo.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            address.setUser(user);
//            user.getAddress().add(address);
//            userRepo.save(user);
//        } else {
//            throw new UserNotFoundException  ("User with ID " + userId + " not found.");
//        }
//    }

}



