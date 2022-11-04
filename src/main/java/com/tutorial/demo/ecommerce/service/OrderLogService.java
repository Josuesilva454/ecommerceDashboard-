package com.tutorial.demo.ecommerce.service;

import com.tutorial.demo.ecommerce.entity.Order;
import com.tutorial.demo.ecommerce.entity.OrderLog;
import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.repository.OrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderLogService {
    @Autowired
    private OrderLogRepository orderLogRepository;

    public final static int DRAFT = 0;
    public final static int PAYMENT = 10;
    public final static int PACKING = 20;
    public final static int DELIVERY = 30;
    public final static int DONE = 40;
    public final static int CANCELLED = 90;

    public void createLog(String username, Order order, int type, String message) {
        OrderLog p = new OrderLog();
        p.setId(UUID.randomUUID().toString());
        p.setLogMessage(message);
        p.setLogType(type);
        p.setOrder(order);
        p.setUser(new User(username));
        p.setTime(new Date());
        orderLogRepository.save(p);
    }

}


