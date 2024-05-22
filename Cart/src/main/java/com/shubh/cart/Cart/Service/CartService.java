package com.shubh.cart.Cart.Service;

import com.Shubh.JPARealtionship.Entity.Cart;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;

public interface CartService {

    public ApiResponse addCart(CartRequest cartRequest);
    public ApiResponse deleteCart(Long id);
    public ApiResponse updateCart(Long id,CartRequest cartRequest);
    public Cart getCart(Long id);
}
