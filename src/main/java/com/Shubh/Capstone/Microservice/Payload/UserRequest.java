package com.Shubh.Capstone.Microservice.Payload;

import com.Shubh.Capstone.Microservice.Beans.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "UserName cannot be Empty")
    @Size(min = 4,message = "Username minimum must be of 4 characters")
    private String user_Name;
    @Email(message ="Use proper email address ")
    private String user_Email;
    @NotEmpty(message = "All fields are mandatory")
    private List<AddressRequest> addressRequests;
}
