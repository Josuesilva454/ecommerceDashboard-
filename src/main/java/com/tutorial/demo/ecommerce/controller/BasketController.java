package com.tutorial.demo.ecommerce.controller;


import com.tutorial.demo.ecommerce.entity.Basket;
import com.tutorial.demo.ecommerce.model.BasketRequest;
import com.tutorial.demo.ecommerce.security.service.UserDetailsImpl;
import com.tutorial.demo.ecommerce.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/baskets")
    public List<Basket> findByUserId(@AuthenticationPrincipal UserDetailsImpl user) {
        return basketService.findByUserId(user.getUsername());
    }

    @PostMapping("/baskets")
    public Basket create(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody BasketRequest request) {
        return basketService.addBasket(user.getUsername(), request.getProductId(), request.getQuantity());
    }

    @PatchMapping("/bakets/{productId}")
    public Basket update(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("productId") String productId,
                            @RequestParam("quantity") Double quantity) {
        return basketService.updateQuantity(user.getUsername(), productId, quantity);
    }

    @DeleteMapping("/baskets/{productId}")
    public void delete(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("productId") String produkId) {
        basketService.delete(user.getUsername(), produkId);
    }

}

