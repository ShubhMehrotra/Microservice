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

import java.util.Optional;

@RestController
@RequestMapping("/Cart")
@Validated
public class Controller {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/addCart")
    public ResponseEntity<ApiResponse> addCart(@Valid  @RequestBody CartRequest cartRequest)
    {
        return ResponseEntity.of(Optional.ofNullable(cartService.addCart(cartRequest)));

    }

    @PostMapping("/updateCart/{id}")
    public  ResponseEntity<ApiResponse> updateCart(@Valid @RequestBody CartRequest cartRequest, @PathVariable Long id)
    {

        return ResponseEntity.of(Optional.ofNullable(cartService.updateCart(cartRequest,id)));
    }

    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long id)
    {

        return ResponseEntity.of(Optional.ofNullable(cartService.deleteCart(id)));
    }
    @GetMapping("/getCart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id)
    {
        return ResponseEntity.of(Optional.ofNullable(cartService.getCart(id)));
    }



}
