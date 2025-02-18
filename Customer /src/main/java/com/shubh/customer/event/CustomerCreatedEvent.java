package com.shubh.customer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreatedEvent {

    private Long userId;          // This will hold the userId after saving the User entity
    private String userName;
    private String userEmail;
    private List<AddressDTO> addressList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressDTO {
        private Long addressId;
        private String street;
        private String city;
        private String postalCode;
        private String country;


    }
}
