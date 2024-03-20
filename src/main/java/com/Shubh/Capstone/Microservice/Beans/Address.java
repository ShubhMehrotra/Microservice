package com.Shubh.Capstone.Microservice.Beans;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long address_Id;
    private String door_No;
    private String street_Name;
    private String city;
    private String pin_Code;




}