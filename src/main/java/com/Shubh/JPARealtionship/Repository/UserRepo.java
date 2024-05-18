package com.Shubh.JPARealtionship.Repository;

import com.Shubh.JPARealtionship.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
