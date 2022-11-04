package com.tutorial.demo.ecommerce.service;

import java.util.List;
import java.util.UUID;

import com.tutorial.demo.ecommerce.entity.Category;
import com.tutorial.demo.ecommerce.exception.ResourceNotFoundException;
import com.tutorial.demo.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria com código\n " + id + " não encontrado\n"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        category.setId(UUID.randomUUID().toString());
        return categoryRepository.save(category);
    }

    public Category edit(Category category) {
        return categoryRepository.save(category);

    }

    public void deleteById(String id) {
        categoryRepository.deleteById(id);


    }
}
