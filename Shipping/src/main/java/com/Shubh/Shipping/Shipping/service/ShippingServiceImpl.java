package com.Shubh.Shipping.Shipping.service;

import com.Shubh.Shipping.Shipping.Client.CartClient;
import com.Shubh.Shipping.Shipping.Client.CustomerClient;
import com.Shubh.Shipping.Shipping.Dto.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class ShippingServiceImpl implements ShippingService{

    private final CartClient cartClient;
    private final CustomerClient customerClient;

    private static final Logger log = LoggerFactory.getLogger(ShippingService.class);

    @Override
    public ApiResponse createCustomer(CustomerDto customerDto) {
        // Step 1: Call Customer Service to create a customer
        ResponseEntity<ApiResponse> createdUserResponse = customerClient.createCustomer(customerDto);

        // ✅ Debugging: Print the full response
        System.out.println("Response from Customer Service: " + createdUserResponse.getBody());

        ApiResponse apiResponse = createdUserResponse.getBody();

        // ✅ Extract userId from ApiResponse
        Long userId = (apiResponse != null) ? apiResponse.getCustomer_Id() : null;

        // ✅ Debugging: Print the extracted userId
        System.out.println("Received ID from Customer Service: " + userId);

        // Step 2: Validate the extracted userId
        if (userId == null) {
            throw new RuntimeException("Customer creation failed: ID is null in response");
        }

        // Step 3: Create CustomerCartDto and Set customerId
        CustomerCartDto customerCartDto = new CustomerCartDto();
        customerCartDto.setCustomerId(userId);  // ✅ Ensure customerId is set before sending to Cart Service
        System.out.println("Customer ID merged in Cart: " + customerCartDto.getCustomerId());

        // ✅ FIX: Instead of setting cart to null, provide an empty CartDto
        customerCartDto.setCart(new CartDto()); // ✅ Ensure cart object is not null

        // Step 4: Call Cart Service to create an empty cart for the new customer
        ResponseEntity<CustomerCartDto> cartResponse = cartClient.createCartForCustomer(customerCartDto);
        CustomerCartDto customerCart = cartResponse.getBody();

        // Step 5: Manually create CustomerDto since Customer Service does not return it
        CustomerDto createdUser = new CustomerDto();
        createdUser.setId(userId);
        createdUser.setUserName(customerDto.getUserName());
        createdUser.setUserEmail(customerDto.getUserEmail());
        createdUser.setAddressRequests(customerDto.getAddressRequests());

        // Step 6: Return the combined response
        return new ApiResponse(HttpStatus.CREATED,"Customer Created Successfully", createdUser.getId());
    }


    @Override
    public UserCartResponse getCustomerWithCart(Long id) {
        try {
            log.info("Fetching customer details for ID: {}", id);

            // Step 1: Fetch customer details
            CustomerDto user = customerClient.getCustomerById(id);

            // ✅ Debugging log before throwing error
            log.info("Response from Customer Service: {}", user);

            if (user == null || user.getId() == null) {
                log.error("Customer not found in Customer Service for ID: {}", id);
                throw new RuntimeException("Customer not found for ID: " + id);
            }

            // Step 2: Fetch cart details
            log.info("Fetching cart details for customer ID: {}", id);
            CustomerCartDto customerCart = cartClient.getCustomerCartByCustomerId(id);

            if (customerCart == null || customerCart.getCart() == null) {
                log.warn("No cart found for customer ID: {}", id);
                customerCart = new CustomerCartDto();
                customerCart.setCart(new CartDto()); // ✅ Ensure cart is not null
            }

            log.info("Returning Customer + Cart details for ID: {}", id);

            // Step 3: Return user + cart details (without `customerCartDto` wrapper)
            return new UserCartResponse(user, customerCart.getCart()); // ✅ Only return `cart`

        } catch (Exception e) {
            log.error("Error fetching user/cart for ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error retrieving customer and cart details", e);
        }
    }

}













