package com.Shubh.Shipping.Shipping.Client;

import com.Shubh.Shipping.Shipping.Dto.ApiResponse;
import com.Shubh.Shipping.Shipping.Dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Customer",path = "/user")
public interface CustomerClient {

    @GetMapping("/getUser/{id}")
    CustomerDto getCustomerById(@PathVariable("id") Long id);

    @PostMapping("/addUser")
    ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerDto customerCartDto); //  POST to create a new customer
}

