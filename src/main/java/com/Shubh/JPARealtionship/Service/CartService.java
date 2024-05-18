package com.Shubh.JPARealtionship.Service;

import com.Shubh.JPARealtionship.Entity.Cart;
import com.Shubh.JPARealtionship.Payload.ApiResponse;
import com.Shubh.JPARealtionship.Payload.CartRequest;

public interface CartService {

    public ApiResponse addCart(CartRequest cartRequest);
    public ApiResponse deleteCart(Long id);
    public ApiResponse updateCart(Long id,CartRequest cartRequest);
    public Cart getCart(Long id);
}
