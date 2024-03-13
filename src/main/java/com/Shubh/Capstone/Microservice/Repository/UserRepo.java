package com.Shubh.Capstone.Microservice.Repository;

import com.Shubh.Capstone.Microservice.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
