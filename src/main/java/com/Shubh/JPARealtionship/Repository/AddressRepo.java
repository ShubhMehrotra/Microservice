package com.Shubh.JPARealtionship.Repository;


import com.Shubh.JPARealtionship.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
