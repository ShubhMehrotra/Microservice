package com.shubh.cart.Cart.Entity;

import com.shubh.cart.Cart.Entity.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Auto-generated primary key

    private Long customerId; // Stores customerId

    @OneToOne
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart cart; // Links to cart
}
