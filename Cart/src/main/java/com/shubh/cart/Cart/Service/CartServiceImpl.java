package com.shubh.cart.Cart.Service;

import com.shubh.cart.Cart.Entity.Cart;
import com.shubh.cart.Cart.Entity.CustomerCart;
import com.shubh.cart.Cart.Entity.LineItem;
import com.shubh.cart.Cart.Payload.ApiResponse;
import com.shubh.cart.Cart.Payload.CartRequest;
import com.shubh.cart.Cart.Payload.LineItemRequest;
import com.shubh.cart.Cart.Repository.CartRepo;
import com.shubh.cart.Cart.Repository.CustomerCartRepo;
import com.shubh.cart.Cart.Exception.CartNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CustomerCartRepo customerCartRepo;

    @Override
    public ApiResponse addCart(CartRequest cartRequest) {
        if (cartRequest == null || cartRequest.getCustomerId() == null) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Cart request or Customer ID is null");
        }

        //  Create an empty cart
        Cart cart = new Cart();
        cart.setLineItems(convertToLineItems(cartRequest.getLineItemRequest()));
        Cart savedCart = cartRepo.save(cart);

        //  Store customerId and cartId in `CustomerCart`
        CustomerCart customerCart = new CustomerCart();
        customerCart.setCustomerId(cartRequest.getCustomerId());
        customerCart.setCart(savedCart);
        customerCartRepo.save(customerCart);

        return new ApiResponse(HttpStatus.CREATED, "Cart Created Successfully");
    }

    @Override
    public ApiResponse deleteCart(Long id) {
        return customerCartRepo.findById(id)
                .map(customerCart -> {
                    cartRepo.deleteById(customerCart.getCart().getCartId());
                    customerCartRepo.deleteById(id);
                    return new ApiResponse(HttpStatus.OK, "Cart Deleted Successfully");
                })
                .orElseThrow(() -> new CartNotFoundException("Cart with ID " + id + " not found in the system"));
    }

    @Override
    public ApiResponse updateCart(CartRequest cartRequest, Long customerId) {
        return Optional.ofNullable(customerCartRepo.findByCustomerId(customerId))
                .map(customerCart -> {
                    Cart existingCart = customerCart.getCart();
                    existingCart.setLineItems(convertToLineItems(cartRequest.getLineItemRequest()));
                    cartRepo.save(existingCart);
                    return new ApiResponse(HttpStatus.OK, "Cart updated successfully");
                })
                .orElseThrow(() -> new CartNotFoundException("Cart for Customer ID " + customerId + " not found in the system"));
    }

    @Override
    public Cart getCart(Long customerId) {
        return Optional.ofNullable(customerCartRepo.findByCustomerId(customerId))
                .map(CustomerCart::getCart)
                .orElseThrow(() -> new CartNotFoundException("No cart found for Customer ID: " + customerId));
    }

    private List<LineItem> convertToLineItems(List<LineItemRequest> lineItemRequests) {
        List<LineItem> lineItems = new ArrayList<>();
        if (lineItemRequests != null) {
            for (LineItemRequest lineItemRequest : lineItemRequests) {
                LineItem item = new LineItem();
                BeanUtils.copyProperties(lineItemRequest, item);
                lineItems.add(item);
            }
        }
        return lineItems;
    }
}
