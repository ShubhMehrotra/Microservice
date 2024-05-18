package com.Shubh.JPARealtionship.Repository;
import com.Shubh.JPARealtionship.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
