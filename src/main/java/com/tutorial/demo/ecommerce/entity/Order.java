package com.tutorial.demo.ecommerce.entity;

import com.tutorial.demo.ecommerce.model.StatusPesana;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.tutorial.demo.ecommerce.model.StatusPesana;
import lombok.Data;


@Data
@Entity(name = "order_id")
public class Order implements Serializable {

    @Id
    private String id;

    private String number;

    @Temporal(TemporalType.DATE)
    private Date date;

    @JoinColumn
    @ManyToOne
    private User user;
    private String shippingAddress;

    private BigDecimal amount; // quantidade
    private BigDecimal shipping;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPesana statusPesana;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOrde;

}
