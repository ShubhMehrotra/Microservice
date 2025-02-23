package com.shubh.cart.Cart.Service;

import com.shubh.cart.Cart.Entity.Cart;
import com.shubh.cart.Cart.Entity.CustomerCart;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;
import com.shubh.cart.Cart.Payload.CustomerCartDto;

public interface CartService {

    public ApiResponse addCart(CustomerCartDto customerCartDto);
    public ApiResponse deleteCart(Long id);
    public ApiResponse updateCart(CartRequest cartRequest,Long id);
    public CustomerCart getCustomerCart(Long customerId);
}
