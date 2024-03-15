package com.Shubh.Capstone.Microservice.Payload;

import com.Shubh.Capstone.Microservice.Beans.Address;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "UserName cannot be Empty")
    private String user_Name;
    @NotEmpty(message ="Email cannot be Empty ")
    private String user_Email;
    @NotEmpty
    private List<Address> addresses;
}
