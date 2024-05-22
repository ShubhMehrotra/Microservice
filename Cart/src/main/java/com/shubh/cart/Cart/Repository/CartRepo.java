package com.shubh.cart.Cart.Repository;

import com.shubh.cart.Cart.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
}
