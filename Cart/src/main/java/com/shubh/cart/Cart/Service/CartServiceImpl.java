package com.shubh.cart.Cart.Service;
import com.Shubh.JPARealtionship.Entity.Address;
import com.Shubh.JPARealtionship.Entity.Cart;
import com.Shubh.JPARealtionship.Entity.LineItem;
import com.Shubh.JPARealtionship.Entity.User;
import com.Shubh.JPARealtionship.Exception.CartNotFoundException;
import com.Shubh.JPARealtionship.Payload.AddressRequest;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;
import com.shubh.cart.Cart.Payload.LineItemRequest;
import com.Shubh.JPARealtionship.Repository.CartRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepo cartRepo;

    @Override
    public ApiResponse addCart(CartRequest cartRequest) {
        return Optional.ofNullable(cartRequest)
                .map(request -> {
                    Cart cart=new Cart();
                    List<LineItem> lineItems = new ArrayList<>();
                    if (cartRequest.getLineItemRequests() != null) {
                        for (LineItemRequest lineItemRequest : cartRequest.getLineItemRequests()) {
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
                .map(cart -> {
                    cartRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK,"Cart deleted successfully");
                }).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Cart Id with this id"+id+"not exist in the system"));
    }

    @Override
    public ApiResponse updateCart(Long id, CartRequest cartRequest) {
        return Optional.ofNullable(cartRequest)
                .map(request->{
                    Cart cart=new Cart();
                    BeanUtils.copyProperties(cartRequest,cart);
                    cartRepo.save(cart);
                    return new ApiResponse(HttpStatus.CREATED,"Cart updated successfully");
                }).orElse(new ApiResponse(HttpStatus.BAD_REQUEST,"Cart Id with this id"+id+"not exist in the system"));
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(()-> new CartNotFoundException("Cart Id with this id"+id+"not exist in the system"));
    }
}
