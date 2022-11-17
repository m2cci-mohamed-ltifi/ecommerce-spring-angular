package com.sop2steria.springbootecommerce.dao;

import com.sop2steria.springbootecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
