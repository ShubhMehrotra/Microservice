package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Exception.AddressNotFoundException;
import com.Shubh.JPARealtionship.Payload.AddressRequest;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Repository.AddressRepo;
import com.Shubh.JPARealtionship.Entity.Address;
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
                    return new ApiResponse(HttpStatus.CREATED, "Address added Successfully");
                })
                .orElse(new ApiResponse(HttpStatus.BAD_REQUEST, "Address failed to get added"));
    }

    @Override
    public ApiResponse deleteAddressById(Long id) {
        return addressRepo.findById(id)
                .map(address -> {
                    addressRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK, "Address deleted Successfully");
                })
                .orElse(new ApiResponse(HttpStatus.NOT_FOUND, "Address with id:" + id + "Not exist in the system"));

    }

    @Override
    public ApiResponse updateAddress(Long id, AddressRequest addressRequest) {
        return Optional.ofNullable(addressRequest)
                .map(address -> {
                    Address address1 = new Address();
                    BeanUtils.copyProperties(addressRequest, address1);
                    addressRepo.save(address1);
                    return new ApiResponse(HttpStatus.CREATED, "Address Updated Successfully");
                })
                .orElse(new ApiResponse(HttpStatus.NOT_FOUND, "Address with id:" + id + "Not exist in the system"));

    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepo.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address with id:" + id + "Not exist in the system"));

    }
}