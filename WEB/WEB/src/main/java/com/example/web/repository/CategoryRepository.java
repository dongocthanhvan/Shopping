package com.example.web.repository;

import com.example.web.entity.Category;
import com.example.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository <Category, Long> {
    List<Category> findByNameContaining(String name);
}
