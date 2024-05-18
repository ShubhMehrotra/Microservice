package com.Shubh.JPARealtionship.Service;


import com.Shubh.JPARealtionship.Entity.Address;
import com.Shubh.JPARealtionship.Payload.AddressRequest;
import com.Shubh.JPARealtionship.Payload.ApiResponse;

public interface AddressService  {
    public ApiResponse addAddress(AddressRequest addressRequest);
    public ApiResponse deleteAddressById(Long id);
    public ApiResponse updateAddress(Long id,AddressRequest addressRequest);
    public Address getAddressById(Long id);

}