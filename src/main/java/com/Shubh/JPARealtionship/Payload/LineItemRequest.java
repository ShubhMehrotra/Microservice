package com.Shubh.JPARealtionship.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItemRequest {
//    @NotNull(message = "Item ID cannot be null")
//    private Long itemId;
    @NotNull(message = "Product ID cannot be null")
    private Long productId;
    @NotEmpty(message = "Product Name cannot be null")
    private String productName;
    @NotNull(message ="Quantity cannot be null")
    private Long quantity;
    @NotNull(message = "Price cannot be null")
    private Long price;
    public boolean isValid() {
        return productName!=null && !productName.isEmpty()
                && quantity!=null
                && price!=null ;
    }

}
