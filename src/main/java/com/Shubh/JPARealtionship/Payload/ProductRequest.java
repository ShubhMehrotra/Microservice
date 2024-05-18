package com.Shubh.JPARealtionship.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotEmpty(message = "Product Name cannot be Empty")
    private String productName;
    @NotEmpty(message ="Product Description cannot be Empty")
    private String productDescription;
    @NotNull(message = "Product price cannot be Null")
    private Long productPrice;
    @NotNull(message = "Quantity cannot be empty")
    private Long quantity;

}
