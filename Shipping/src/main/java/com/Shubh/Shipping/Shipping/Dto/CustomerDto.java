package com.Shubh.Shipping.Shipping.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    @JsonProperty("id")
    private Long id;
    @NotEmpty(message = "UserName cannot be empty")
    @Size(min = 4, message = "Username must be at least 4 characters")
    private String userName;

    @Email(message = "Use a valid email address")
    @NotEmpty(message = "UserEmail cannot be empty")
    private String userEmail;

    @Valid
    @NotEmpty(message = "Address list cannot be empty")
    private List<AddressDto> addressRequests;
}
