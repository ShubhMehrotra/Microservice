package com.shubh.cart.Cart.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineItemRequest {


    @NotNull(message = "ID cannot be Null")
    private Long productId;
    @NotEmpty(message = "Name cannot be empty")
    private String productName;
    @NotNull(message ="Quantity cannot be null")
    private Long quantity;
    @NotNull(message = "Price cannot be null ")
    private Long price;


}
