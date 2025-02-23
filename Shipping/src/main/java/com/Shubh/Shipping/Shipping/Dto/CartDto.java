package com.Shubh.Shipping.Shipping.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long cartId;
    private List<LineItemDto> lineItems;
}
