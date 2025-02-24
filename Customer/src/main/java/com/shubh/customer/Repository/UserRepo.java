package com.shubh.customer.Repository;
import com.shubh.customer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
