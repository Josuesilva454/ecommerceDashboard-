package com.tutorial.demo.ecommerce.repository;

import com.tutorial.demo.ecommerce.entity.Basket;
import com.tutorial.demo.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}

