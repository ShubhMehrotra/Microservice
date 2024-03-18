package com.Shubh.Capstone.Microservice.Beans;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long address_Id;
    private String door_No;
    private String street_Name;
    private String city;
    private String pin_Code;




}