package com.shubh.cart.Cart.Payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shubh.cart.Cart.Entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerCartDto {
    private Long customerId;
    private Cart cart;
}
