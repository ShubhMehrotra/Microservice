package com.shubh.cart.Cart.Payload;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    @NotEmpty(message = "All fields are mandatory")
    private List<LineItemRequest> lineItemRequest;

}
