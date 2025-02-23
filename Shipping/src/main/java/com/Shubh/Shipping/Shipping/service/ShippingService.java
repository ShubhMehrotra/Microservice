package com.Shubh.Shipping.Shipping.service;
import com.Shubh.Shipping.Shipping.Dto.ApiResponse;
import com.Shubh.Shipping.Shipping.Dto.CustomerDto;
import com.Shubh.Shipping.Shipping.Dto.UserCartResponse;



public interface ShippingService {
    UserCartResponse getCustomerWithCart(Long customerId);

    ApiResponse createCustomer(CustomerDto customerDto);

}