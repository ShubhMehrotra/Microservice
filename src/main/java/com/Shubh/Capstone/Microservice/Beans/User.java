package com.Shubh.Capstone.Microservice.Beans;

import com.Shubh.Capstone.Microservice.Payload.AddressRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @Column(name="user_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_Id;
    private String user_Name;
    private String user_Email;



    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name="user_Id",nullable = false)
    private List<Address> Address = new ArrayList<>();;




}