package com.nrt.icecream.repository;

import com.nrt.icecream.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
