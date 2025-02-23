package com.Shubh.Shipping.Shipping.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItemDto {

    private Long productId;
    private String productName;
    private Long quantity;
    private Long price;

}
