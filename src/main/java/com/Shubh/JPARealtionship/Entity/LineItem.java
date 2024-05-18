package com.Shubh.JPARealtionship.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable=false, updatable=false)
    private Long itemId;
    private Long productId;
    private String productName;
    private Long quantity;
    private Long price;



}
