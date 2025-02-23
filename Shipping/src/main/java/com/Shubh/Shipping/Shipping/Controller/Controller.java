package com.Shubh.Shipping.Shipping.Controller;


import com.Shubh.Shipping.Shipping.Dto.ApiResponse;
import com.Shubh.Shipping.Shipping.Dto.CustomerDto;
import com.Shubh.Shipping.Shipping.Dto.UserCartResponse;
import com.Shubh.Shipping.Shipping.service.ShippingServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
@AllArgsConstructor
public class Controller {

    private final ShippingServiceImpl shippingService;

    @GetMapping("/getCustomer/{id}")
    public UserCartResponse getCustomerWithCart(@PathVariable Long id) {
        return shippingService.getCustomerWithCart(id);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody CustomerDto userDto) {
        ApiResponse response = shippingService.createCustomer(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
