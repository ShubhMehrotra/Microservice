package com.Shubh.JPARealtionship.Payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    @NotEmpty(message = "All fields are mandatory")
    private List<@Valid LineItemRequest> lineItemRequests;

    public boolean isValidLineItemRequests() {
        return lineItemRequests != null &&
                lineItemRequests.stream().allMatch(Objects::nonNull) &&
                lineItemRequests.stream().allMatch(LineItemRequest::isValid);
    }
}

