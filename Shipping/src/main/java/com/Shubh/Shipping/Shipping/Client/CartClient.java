package com.Shubh.Shipping.Shipping.Client;

import com.Shubh.Shipping.Shipping.Dto.CustomerCartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Cart",path = "/cart",url = "http://localhost:8085")
public interface CartClient {

    @GetMapping("/getCart/{customerId}")
    CustomerCartDto getCustomerCartByCustomerId(@PathVariable("customerId") Long customerId);

    @PostMapping(value = "/addCart", consumes = "application/json", produces = "application/json")
    ResponseEntity<CustomerCartDto> createCartForCustomer(@RequestBody CustomerCartDto customerCartDto);


}



