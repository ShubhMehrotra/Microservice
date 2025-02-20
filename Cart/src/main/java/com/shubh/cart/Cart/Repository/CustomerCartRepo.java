package com.shubh.cart.Cart.Repository;

import com.shubh.cart.Cart.Entity.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepo extends JpaRepository<CustomerCart,Long> {
    CustomerCart findByCustomerId(Long customerId);
}
