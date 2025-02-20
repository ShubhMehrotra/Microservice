package com.shubh.cart.Cart.Payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private List<LineItemRequest> lineItemRequest;

    @NotNull(message = "Customer ID is mandatory")
    private Long customerId;

}
