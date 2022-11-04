package com.tutorial.demo.ecommerce.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest implements Serializable {

    private BigDecimal shipping;
    private String shippingaddress;

    private List<BasketRequest> items;


}
