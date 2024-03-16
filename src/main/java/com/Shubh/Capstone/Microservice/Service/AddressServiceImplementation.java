package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Exception.AddressNotFoundException;
import com.Shubh.Capstone.Microservice.Payload.AddressRequest;
import com.Shubh.Capstone.Microservice.Payload.AddressResponse;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;
import com.Shubh.Capstone.Microservice.Repository.AddressRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            return new AddressResponse("FAILED", HttpStatus.OK);
        addressRepo.deleteById(id);
        return  new AddressResponse("SUCCESS",HttpStatus.OK);
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        Optional<Address> add=addressRepo.findById(id);
        if(add.isEmpty())
            return new AddressResponse("FAILED", HttpStatus.OK);
        Address address=add.get();
        BeanUtils.copyProperties(addressRequest,add);
        addressRepo.save(address);
        return  new AddressResponse("SUCCESS",HttpStatus.OK);


    }

    @Override
    public List<AddressRequest> getAddress() {
        List<Address> addresses = addressRepo.findAll();
        if (addresses.isEmpty())
            throw new AddressNotFoundException("No Address found in System");

        List<AddressRequest> addressRequests = new ArrayList<>();
        for (Address address : addresses) {
            AddressRequest addressRequest = new AddressRequest();
            BeanUtils.copyProperties(address, addressRequest);
            addressRequests.add(addressRequest);
        }

        return addressRequests;
    }


    @Override
    public AddressResponse getAddressById(Long id) {
        Address address=addressRepo.findById(id)
                .orElseThrow(()-> new AddressNotFoundException("Address not found against this ID" + id));
        return new AddressResponse("Address found for this ID-"+ id, HttpStatus.OK);
    }
}
