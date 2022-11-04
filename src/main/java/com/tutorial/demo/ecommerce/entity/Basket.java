package com.tutorial.demo.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Basket implements Serializable {



    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    private Product product;
    @JoinColumn
    @ManyToOne
    private User user;
    private Double quantity;
    private BigDecimal price;
    private BigDecimal amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
}

