package com.shubh.cart.Cart.Controller;

import com.shubh.cart.Cart.Entity.Cart;
import com.shubh.cart.Cart.Entity.CustomerCart;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;
import com.shubh.cart.Cart.Payload.CustomerCartDto;
import com.shubh.cart.Cart.Service.CartServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Validated
public class Controller {

    @Autowired
    private CartServiceImpl cartService;


//    @PostMapping(value = "/addCart", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<ApiResponse> addCart(@Valid @RequestBody CartRequest cartRequest) {
//        ApiResponse response = cartService.addCart(cartRequest);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping(value = "/addCart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addCart(@Valid @RequestBody CustomerCartDto customerCartDto) {
        System.out.println("📥 Received request to create cart: " + customerCartDto);

        if (customerCartDto.getCustomerId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(HttpStatus.BAD_REQUEST, "❌ Customer ID is required to create a cart"));
        }

        ApiResponse response = cartService.addCart(customerCartDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @PostMapping("/updateCart/{id}")
    public ResponseEntity<ApiResponse> updateCart(@Valid @RequestBody CartRequest cartRequest, @PathVariable Long id) {
        ApiResponse response = cartService.updateCart(cartRequest, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long id) {
        ApiResponse response = cartService.deleteCart(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getCart/{customerId}")
    public ResponseEntity<CustomerCart> getCartByCustomerId(@PathVariable Long customerId) {
        CustomerCart customerCart = cartService.getCustomerCart(customerId);

        if (customerCart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerCart);
    }

}
