package com.example.web.repository;

import com.example.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByCategory_Id(Long id);
}
