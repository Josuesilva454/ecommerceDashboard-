package com.tutorial.demo.ecommerce.controller;

import java.util.List;

import com.tutorial.demo.ecommerce.entity.Product;
import com.tutorial.demo.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class ProductController {

    @Autowired
    private ProductService produkService;

    @GetMapping("/produks")
    public List<Product> findAll() {
        return produkService.findAll();
    }

    @GetMapping("/produks/{id}")
    public Product findById(@PathVariable("id") String id) {
        return produkService.findById(id);
    }

    @PostMapping("/produks")
    public Product create(@RequestBody Product produk) {
        return produkService.create(produk);
    }

    @PutMapping("/produks")
    public Product edit(@RequestBody Product produk) {
        return produkService.edit(produk);
    }

    @DeleteMapping("/produks/{id}")
    public void deleteById(@PathVariable("id") String id) {
        produkService.deleteById(id);
    }

}
