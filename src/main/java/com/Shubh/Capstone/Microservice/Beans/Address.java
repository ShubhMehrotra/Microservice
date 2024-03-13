package com.Shubh.Capstone.Microservice.Beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    private Long address_Id;
    private String door_No;
    private String street_Name;
    private String city;
    private String pin_Code;
}
