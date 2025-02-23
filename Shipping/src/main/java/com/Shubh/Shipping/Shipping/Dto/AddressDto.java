package com.Shubh.Shipping.Shipping.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String doorNo;
    private String streetName;
    private String city;
    private String pinCode;
}
