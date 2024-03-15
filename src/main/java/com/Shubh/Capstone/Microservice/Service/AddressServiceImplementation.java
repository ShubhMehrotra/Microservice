package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Exception.AddressNotFoundException;
import com.Shubh.Capstone.Microservice.Payload.AddressResponse;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService{

    @Autowired
    private AddressRepo addressRepo;
    @Override
    public Address addAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public AddressResponse deleteAddressById(Long id) {
        Optional<Address> address=addressRepo.findById(id);
        if(address.isEmpty())
            return new AddressResponse("FAILED", "Address to be deleted not found in System");
        addressRepo.deleteById(id);
        return  new AddressResponse("SUCCESS","Address Deleted");
    }

    @Override
    public AddressResponse updateAddress(Long id, Address address) {
        Optional<Address> add=addressRepo.findById(id);
        if(add.isEmpty())
            return new AddressResponse("FAILED", "Address to be Updated not found in System");
        Address updatedAddress=addressRepo.save(address);
        return  new AddressResponse("SUCCESS","Address Updated");


    }

    @Override
    public List<Address> getAddress() {
     List<Address> addresses=addressRepo.findAll();
     if(addresses.isEmpty())
         throw new AddressNotFoundException("No Address found in System");
     return addresses;
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepo.findById(id)
                .orElseThrow(()-> new AddressNotFoundException("Address not found against this ID" + id));
    }
}
