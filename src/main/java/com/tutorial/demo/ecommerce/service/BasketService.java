package com.tutorial.demo.ecommerce.service;

import com.tutorial.demo.ecommerce.entity.Basket;
import com.tutorial.demo.ecommerce.entity.Product;
import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.exception.BadRequestException;
import com.tutorial.demo.ecommerce.repository.BasketRepository;
import com.tutorial.demo.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;


    @Transactional
    public  Basket addBasket(String username, String productId, Double quantity) {
        Product produk = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Produto ID " + productId + " não existe"));

        Optional<Basket> optional = basketRepository.findByUserIdAndProductId(username, productId);
        Basket basket;

        if (optional.isPresent()) {
            basket = optional.get();
            basket.setQuantity(basket.getQuantity() + quantity);
            basket.setAmount(new BigDecimal(basket.getPrice().doubleValue() * basket.getQuantity()));
            basketRepository.save(basket);
        } else {
            basket = new Basket();
            basket.setId(UUID.randomUUID().toString());
            basket.setProduct(produk);
            basket.setQuantity(quantity);
            basket.setPrice(produk.getPrice());
            basket.setAmount(new BigDecimal(basket.getPrice().doubleValue() * basket.getQuantity()));
            basket.setUser(new User(username));
            basketRepository.save(basket);
        }


        return basket;

    }

    @Transactional
    public Basket updateQuantity(String username, String productId, Double quantity) {
        Basket basket = basketRepository.findByUserIdAndProductId(username, productId)
                .orElseThrow(() -> new BadRequestException(
                        "Product ID " + productId + " não encontrado em seu carrinho"));
        basket.setQuantity(quantity);
        basket.setAmount(new BigDecimal(basket.getPrice().doubleValue() * basket.getQuantity()));
        basketRepository.save(basket);
        return basket;
    }

    @Transactional
    public void delete(String username, String productId) {
        Basket basket = basketRepository.findByUserIdAndProductId(username, productId)
                .orElseThrow(() -> new BadRequestException(
                        "Produk ID " + productId + " não encontrado em seu carrinho\n"));

         basketRepository.delete(basket);
    }

    public List<Basket> findByUserId(String username) {
        return basketRepository.findByUserId(username);
    }

}

