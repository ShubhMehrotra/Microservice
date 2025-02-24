package com.shubh.customer.Payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotEmpty(message = "Street door No cannot be empty")
    private String doorNo;
    @NotEmpty(message = "Street Name cannot be empty")
    private String streetName;
    @NotEmpty(message = "City name cannot be empty")
    private String city;
    @NotEmpty(message = "Pin Code cannot be empty")
    private String pinCode;



}