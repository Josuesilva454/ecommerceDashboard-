package com.tutorial.demo.ecommerce.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class OrderItem implements Serializable {
    @Id
    private String id;

    @JoinColumn
    @ManyToOne
    private Order order;
    @JoinColumn
    @ManyToOne
    private Product product;
    private String description;
    private Double quantity;
    private BigDecimal price;
    private BigDecimal amount;

}

