package com.Shubh.JPARealtionship.Repository;

import com.Shubh.JPARealtionship.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {
}
