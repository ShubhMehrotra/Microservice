package com.shubh.cart.Cart.Controller;

import com.shubh.cart.Cart.Entity.Cart;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;
import com.shubh.cart.Cart.Service.CartServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Validated
public class Controller {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/addCart")
    public ResponseEntity<ApiResponse> addCart(@Valid @RequestBody CartRequest cartRequest) {
        ApiResponse response = cartService.addCart(cartRequest);
        return ResponseEntity.ok(response);
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

    @GetMapping("/getCart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return cartService.getCart(id) != null
                ? ResponseEntity.ok(cartService.getCart(id))
                : ResponseEntity.notFound().build();
    }
}
