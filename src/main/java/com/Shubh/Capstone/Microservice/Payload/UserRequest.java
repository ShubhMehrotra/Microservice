package com.Shubh.Capstone.Microservice.Payload;

import com.Shubh.Capstone.Microservice.Beans.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotEmpty(message = "UserName cannot be Empty")
    @Size(min = 4,message = "Username minimum must be of 4 characters")
    private String user_Name;
    @Email(message ="Use proper email address ")
    @NotEmpty(message = "UserEmail cannot be Empty")
    private String user_Email;
    @NotEmpty(message = "All fields are mandatory")
    private List<@Valid AddressRequest> addressRequests;

    public boolean isValidAddressRequests() {
        return addressRequests != null &&
                addressRequests.stream().allMatch(Objects::nonNull) &&
                addressRequests.stream().allMatch(AddressRequest::isValid);
    }
}
