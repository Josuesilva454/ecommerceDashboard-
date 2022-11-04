package com.tutorial.demo.ecommerce.model;


import com.tutorial.demo.ecommerce.entity.Order;
import com.tutorial.demo.ecommerce.entity.OrderItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse implements Serializable {


    private String id;
    private String Ordernumber;
    private Date date;
    private String CustomerName;
    private String Shippingaddress;
    private Date timeOrde;
    private BigDecimal amount;
    private BigDecimal shipping;
    private BigDecimal total;

    private List<OrderResponse.Item> items;

    public OrderResponse(Order order, List<OrderItem> orderItems) {
        this.id = order.getId();
        this.Ordernumber = order.getNumber();
        this.date = order.getDate();
        this.CustomerName = order.getUser().getName();
        this.Shippingaddress = order.getShippingAddress();
        this.timeOrde = order.getTimeOrde();
        this.amount = order.getAmount();
        this.shipping = order.getShipping();
        this.total = order.getTotal();
        items = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            Item item = new Item();
            item.setProductId(orderItem.getProduct().getId());
            item.setNameProduct(orderItem.getDescription());
            item.setQuantity(orderItem.getQuantity());
            item.setPrice(orderItem.getPrice());
            item.setAmount(orderItem.getAmount());
            items.add(item);
        }
    }

    @Data
    public static class Item implements Serializable {
        private String productId;
        private String nameProduct;
        private Double quantity ;
        private BigDecimal price;
        private BigDecimal amount;


    }

}
