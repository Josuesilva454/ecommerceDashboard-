package com.tutorial.demo.ecommerce.repository;

import com.tutorial.demo.ecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String> {
    Optional<Basket> findByUserIdAndProductId(String username, String productId);

    List<Basket> findByUserId(String username);
}
