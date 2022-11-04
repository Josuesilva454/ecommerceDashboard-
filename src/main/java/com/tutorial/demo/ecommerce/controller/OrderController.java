package com.tutorial.demo.ecommerce.controller;

import com.tutorial.demo.ecommerce.entity.Order;
import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.model.OrderRequest;
import com.tutorial.demo.ecommerce.model.OrderResponse;
import com.tutorial.demo.ecommerce.security.service.UserDetailsImpl;
import com.tutorial.demo.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    @PreAuthorize("hasAuthority('user')")
    public OrderResponse create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody OrderRequest request) {
        return orderService.create(user.getUsername(), request);
    }

    @PatchMapping("/orders/{orderId}/cancel")
    @PreAuthorize("hasAuthority('user')")
    public Order cancelOrderUser(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("orderId") String orderId) {
        return orderService.cancelOrder(orderId, user.getUsername());
    }

    @PatchMapping("/orders/{orderId}/accept")
    @PreAuthorize("hasAuthority('user')")
    public Order terima(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("orderId") String pesananId) {
        return orderService.terimaOrder(pesananId, user.getUsername());
    }

    @PatchMapping("/orders/{orderId}/confirmation")
    @PreAuthorize("hasAuthority('admin')")
    public Order konfirmasi(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("orderId") String orderId) {
        return orderService.paymentconfirmation(orderId, user.getUsername());
    }

    @PatchMapping("/orders/{orderId}/packing")
    @PreAuthorize("hasAuthority('admin')")
    public Order packing(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("orderId") String pesananId) {
        return orderService.packing(pesananId, user.getUsername());
    }

    @PatchMapping("/orders/{orderId}/kirim")
    @PreAuthorize("hasAuthority('admin')")
    public Order send(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("orderId") String pesananId) {
        return orderService.send(pesananId, user.getUsername());
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('user')")
    public List<Order> findAllPesananUser(@AuthenticationPrincipal UserDetailsImpl user,
                                            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(name = "limit", defaultValue = "25", required = false) int limit) {
        return orderService.findAllorderUser(user.getUsername(), page, limit);
    }

    @GetMapping("/orders/admin")
    @PreAuthorize("hasAuthority('admin')")
    public List<Order> search(@AuthenticationPrincipal UserDetailsImpl user,
                              @RequestParam(name = "filterText", defaultValue = "", required = false) String filterText,
                              @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                              @RequestParam(name = "limit", defaultValue = "25", required = false) int limit) {
        return orderService.search(filterText, page, limit);
    }

}

