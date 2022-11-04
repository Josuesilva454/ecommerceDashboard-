package com.tutorial.demo.ecommerce.repository;

import com.tutorial.demo.ecommerce.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, String> {
}