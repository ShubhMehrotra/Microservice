package com.shubh.customer.Service;
import com.shubh.customer.Entity.Address;
import com.shubh.customer.Payload.AddressRequest;
import com.shubh.customer.Payload.ApiResponse;

public interface AddressService  {
    public ApiResponse addAddress(AddressRequest addressRequest);
    public ApiResponse deleteAddressById(Long id);
    public ApiResponse updateAddress(Long id,AddressRequest addressRequest);
    public Address getAddressById(Long id);

}