package com.Shubh.JPARealtionship.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "productID")
    private Product product;
    private int quantity;

}
