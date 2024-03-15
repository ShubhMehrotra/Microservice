package com.Shubh.Capstone.Microservice.Service;

import com.Shubh.Capstone.Microservice.Beans.Address;
import com.Shubh.Capstone.Microservice.Beans.User;
import com.Shubh.Capstone.Microservice.Payload.AddressResponse;
import com.Shubh.Capstone.Microservice.Payload.UserResponse;

import java.util.List;

public interface AddressService  {
    public Address addAddress( Address address);
    public AddressResponse deleteAddressById(Long id);
    public AddressResponse updateAddress(Long id,Address address);
    public List<Address> getAddress();
    public Address getAddressById(Long id);

}
