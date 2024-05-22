package com.shubh.cart.Cart.Service;


import com.shubh.cart.Cart.Entity.Cart;
import com.shubh.cart.Cart.Entity.LineItem;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;
import com.shubh.cart.Cart.Payload.LineItemRequest;
import com.shubh.cart.Cart.Repository.CartRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.shubh.cart.Cart.Exception.CartNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;


    @Override
    public ApiResponse addCart(CartRequest cartRequest) {
        return Optional.ofNullable(cartRequest)
                .map(request -> {
                    Cart cart=new Cart();
                    List<LineItem> lineItems = new ArrayList<>();
                    if (cartRequest.getLineItemRequest() != null) {
                        for (LineItemRequest lineItemRequest :cartRequest.getLineItemRequest()) {
                            LineItem item=new LineItem();
                            BeanUtils.copyProperties(lineItemRequest, item);
                            lineItems.add(item);
                        }
                    }
                    cart.setLineItems(lineItems);
                    BeanUtils.copyProperties(cartRequest, cart);
                    cartRepo.save(cart);
                    return new ApiResponse(HttpStatus.CREATED, "Cart Created Successfully");
                })
                .orElse(new ApiResponse(HttpStatus.BAD_REQUEST, "Cart request is null"));
    }


    @Override
    public ApiResponse deleteCart(Long id) {
        return cartRepo.findById(id)
                .map(cart->{
                    cartRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK,"Cart Deleted Successfully");
                }).orElseThrow(()->new CartNotFoundException("Cart with this ID"+id+"not exist in the System"));
    }

    @Override
    public ApiResponse updateCart(CartRequest cartRequest, Long id) {
        return cartRepo.findById(id)
                .map(request->{
                    Cart cart=new Cart();
                    List<LineItem> lineItems=new ArrayList<>();
                    if(cartRequest.getLineItemRequest()!=null)
                    {
                        for(LineItemRequest lineItemRequest:cartRequest.getLineItemRequest())
                        {
                            LineItem item=new LineItem();
                            BeanUtils.copyProperties(lineItemRequest,item);
                            lineItems.add(item);
                        }
                    }
                    cart.setLineItems(lineItems);
                    BeanUtils.copyProperties(cartRequest,cart);
                    cartRepo.save(cart);
                    return new ApiResponse(HttpStatus.CREATED,"Cart Updated Successfully");
                }).orElseThrow(()->new CartNotFoundException("Cart with this ID"+id+"not exist in the System"));
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(()->new CartNotFoundException("Cart with this ID"+id+"not exist in the System"));
    }
}
