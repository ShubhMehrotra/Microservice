package com.shubh.cart.Cart.kafka;

import com.shubh.cart.Cart.Entity.Cart;
import com.shubh.cart.Cart.Entity.CustomerCart;
import com.shubh.cart.Cart.Repository.CartRepo;
import com.shubh.cart.Cart.Repository.CustomerCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CartEventConsumer {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CustomerCartRepo customerCartRepo;

    @KafkaListener(topics = "user-created", groupId = "cart-service-group")
    public void consumeUserCreatedEvent(Long customerId) {
        System.out.println("Received Kafka event: Creating empty cart for customerId " + customerId);

        // ✅ Step 1: Create an empty cart
        Cart cart = new Cart();
        cartRepo.save(cart);

        // ✅ Step 2: Store customerId and cartId in `CustomerCart`
        CustomerCart customerCart = new CustomerCart();
        customerCart.setCustomerId(customerId);
        customerCart.setCart(cart);
        customerCartRepo.save(customerCart);
    }
}
