package com.Shubh.Capstone.Microservice.Beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private Long user_Id;
    private String user_Name;
    private String user_Email;
}