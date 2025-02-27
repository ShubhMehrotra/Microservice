package com.shubh.customer.Service;


import com.shubh.customer.Entity.Address;
import com.shubh.customer.Exception.AddressNotFoundException;
import com.shubh.customer.Payload.AddressRequest;
import com.shubh.customer.Payload.ApiResponse;

import com.shubh.customer.Repository.AddressRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public ApiResponse addAddress(AddressRequest addressRequest) {
        return Optional.ofNullable(addressRequest)
                .map(request ->
                {
                    Address address = new Address();
                    BeanUtils.copyProperties(addressRequest, address);
                    addressRepo.save(address);
                    return new ApiResponse(HttpStatus.CREATED, "Address added Successfully",null);
                })
                .orElse(new ApiResponse(HttpStatus.BAD_REQUEST, "Address failed to get added",null));
    }

    @Override
    public ApiResponse deleteAddressById(Long id) {
        return addressRepo.findById(id)
                .map(address -> {
                    addressRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK, "Address deleted Successfully",null);
                })
                .orElse(new ApiResponse(HttpStatus.NOT_FOUND, "Address with id:" + id + "Not exist in the system",null));

    }

    @Override
    public ApiResponse updateAddress(Long id, AddressRequest addressRequest) {
        return Optional.ofNullable(addressRequest)
                .map(address -> {
                    Address address1 = new Address();
                    BeanUtils.copyProperties(addressRequest, address1);
                    addressRepo.save(address1);
                    return new ApiResponse(HttpStatus.CREATED, "Address Updated Successfully",null);
                })
                .orElse(new ApiResponse(HttpStatus.NOT_FOUND, "Address with id:" + id + "Not exist in the system",null));

    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepo.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address with id:" + id + "Not exist in the system"));

    }
}