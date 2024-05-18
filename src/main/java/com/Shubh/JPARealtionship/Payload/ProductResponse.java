package com.Shubh.JPARealtionship.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductResponse {

    @NotEmpty(message ="Product Name cannot be empty")
    private String productName;
    @NotEmpty(message = "Product Description cannot be empty")
    private String productDescription;
    @NotEmpty(message ="Product Price cannot be empty")
    @Size(min = 1)
    private Long productPrice;

}
