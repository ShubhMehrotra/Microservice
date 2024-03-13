package com.Shubh.Capstone.Microservice.Beans;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

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

}