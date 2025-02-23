package com.shubh.customer.dto;

import com.shubh.customer.Payload.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;  // Change from userId to id for consistency with Feign Client
    private String userName;
    private String userEmail;
    private List<AddressRequest> addressRequests;
}


