package com.tutorial.demo.ecommerce.controller;

import com.tutorial.demo.ecommerce.entity.Category;
import com.tutorial.demo.ecommerce.service.CategoryService;
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

import java.util.List;

//http:localhost:8080/api/category

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> findAll(){
        return categoryService.findAll();
    }
    @GetMapping("/category/{id}")
    public Category findById(@PathVariable ("id") String id){
        return  categoryService.findById(id);
    }

    @PostMapping ("/category")
    public Category create (@RequestBody Category category){
        return  categoryService.create(category);
    }
    @PutMapping("/category")
    public  Category edit (@RequestBody Category category){
        return  categoryService.edit(category);
    }
    @DeleteMapping("/category/{id}")
    public  void deleteById(@PathVariable("id") String id){
        categoryService.deleteById(id);
    }
}


