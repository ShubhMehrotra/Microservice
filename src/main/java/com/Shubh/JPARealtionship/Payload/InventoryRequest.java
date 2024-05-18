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

public class InventoryRequest {

    @NotNull(message ="Id cannot be empty")
    private Long productId;
    @NotNull(message = "Quantity cannot be empty")
    private Long quantity;
}
