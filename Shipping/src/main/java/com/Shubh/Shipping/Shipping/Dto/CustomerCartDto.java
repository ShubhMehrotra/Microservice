package com.Shubh.Shipping.Shipping.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerCartDto {

    @JsonProperty("customerId")
    private Long customerId;
    private CartDto cart;
    private CustomerDto customerDto;
}
