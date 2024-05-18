package com.Shubh.JPARealtionship.Controller;

import com.Shubh.JPARealtionship.Entity.Cart;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.CartRequest;
import com.Shubh.JPARealtionship.Service.CartServiceImpl;
import com.Shubh.JPARealtionship.Service.InventoryImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/addCart")
    public ResponseEntity<ApiResponse> addCart(@Valid @RequestBody CartRequest cartRequest )
    {
        return ResponseEntity.ofNullable(cartService.addCart(cartRequest));

    }

    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@Valid @PathVariable Long id)
    {
        return ResponseEntity.ofNullable(cartService.deleteCart(id));
    }

    @PutMapping("/updateCart/{id}")
    public ResponseEntity<ApiResponse> updateCart(@Valid CartRequest cartRequest ,@PathVariable Long id)
    {
        return ResponseEntity.ofNullable(cartService.updateCart(id, cartRequest));

    }

    @GetMapping("/getCart/{id}")
    public  ResponseEntity<Cart> getCart(@PathVariable Long id)
    {

        return ResponseEntity.of(Optional.ofNullable(cartService.getCart(id)));
    }





}
