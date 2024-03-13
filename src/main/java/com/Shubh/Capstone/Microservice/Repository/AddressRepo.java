package com.Shubh.Capstone.Microservice.Repository;

import com.Shubh.Capstone.Microservice.Beans.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
