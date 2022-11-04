package com.tutorial.demo.ecommerce.service;

import com.tutorial.demo.ecommerce.entity.Order;
import com.tutorial.demo.ecommerce.entity.OrderItem;
import com.tutorial.demo.ecommerce.entity.Product;
import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.exception.BadRequestException;
import com.tutorial.demo.ecommerce.exception.ResourceNotFoundException;
import com.tutorial.demo.ecommerce.model.BasketRequest;
import com.tutorial.demo.ecommerce.model.OrderRequest;
import com.tutorial.demo.ecommerce.model.OrderResponse;
import com.tutorial.demo.ecommerce.model.StatusPesana;
import com.tutorial.demo.ecommerce.repository.OrderItemRepository;
import com.tutorial.demo.ecommerce.repository.OrderRepository;
import com.tutorial.demo.ecommerce.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderLogService orderLogService;

    @Autowired
    private BasketService basketService;


    @Transactional
    public OrderResponse create(String username, OrderRequest request) {

        Order order = new Order();

        order.setId(UUID.randomUUID().toString());
        order.setDate(new Date());
        order.setNumber(generateNumberOrder());
        order.setUser(new User(username));
        order.setShippingAddress(request.getShippingaddress());
        order.setStatusPesana(StatusPesana.DRAFT);
        order.setTimeOrde(new Date());

        List<OrderItem> items = new ArrayList<>();
        for(BasketRequest k: request.getItems()){
            Product product = productRepository.findById(k.getProductId())
                    .orElseThrow(() -> new BadRequestException("Produto id" + k.getProductId() + "Não existe" ));
            if (product.getStock() < k.getQuantity()){
                throw new BadRequestException(("Estoque insuficiente\n"));
            }
            OrderItem pi = new OrderItem();
            pi.setId(UUID.randomUUID().toString());
            pi.setProduct(product);
            pi.setDescription(product.getName());
            pi.setQuantity(k.getQuantity());
            pi.setPrice(product.getPrice());
            pi.setAmount(new BigDecimal(pi.getPrice().doubleValue() * pi.getQuantity()));
            pi.setOrder(order);
            items.add(pi);
        }

        BigDecimal amount = BigDecimal.ZERO;
        for (OrderItem orderItem : items){
            amount = amount.add(orderItem.getAmount());
        }
        order.setAmount(amount);
        order.setShipping(request.getShipping());
        order.setTotal(order.getAmount().add(order.getShipping()));

        Order save = orderRepository.save(order);
        for (OrderItem orderItem : items){
            orderItemRepository.save(orderItem);
            Product product = orderItem.getProduct();
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);
            basketService.delete(username, product.getId());
        }
        //log
        orderLogService.createLog(username, order, OrderLogService.DRAFT, "Pedido feito com sucesso\n");
        OrderResponse orderResponse = new OrderResponse(save, items);
        return orderResponse;
    }


    @Transactional
    public Order cancelOrder(String orderId,  String userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem ID" + orderId + "Não existe"));
        if (!userId.equals(order.getUser().getId())){
            throw new BadRequestException("Este pedido só pode ser cancelado pelo interessado\n");
        }
        if(!StatusPesana.DRAFT.equals(order.getStatusPesana())){
            throw new BadRequestException("Este pedido não pode ser cancelado porque já foi processado\n");

        }
        order.setStatusPesana(StatusPesana.CANCELLED );
        Order save = orderRepository.save(order);
        orderLogService.createLog(userId, save, OrderLogService.CANCELLED, "Pedido cancelado com sucesso");
        return save;
    }

    public Order terimaOrder(String orderId, String username) {

        return null;
    }

    public Order paymentconfirmation(String orderId, String username) {

        return null;
    }

    public Order packing(String orderId, String username) {

        return null;
    }

    public Order send(String ordernId, String username) {

        return null;
    }

        private String generateNumberOrder() {
        return String.format("%016d", System.nanoTime());

    }

    public List<Order> search(String filterText, int page, int limit) {

        return null;
    }

    public List<Order> findAllorderUser(String username, int page, int limit) {
        return null;
    }
}
