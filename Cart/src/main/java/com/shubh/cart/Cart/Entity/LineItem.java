package com.shubh.cart.Cart.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private Long productId;
    private String productName;
    private Long quantity;
    private Long price;


}
