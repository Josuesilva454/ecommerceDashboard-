package com.tutorial.demo.ecommerce.service;

import java.util.List;
import java.util.UUID;

import com.tutorial.demo.ecommerce.entity.Product;
import com.tutorial.demo.ecommerce.exception.BadRequestException;
import com.tutorial.demo.ecommerce.exception.ResourceNotFoundException;
import com.tutorial.demo.ecommerce.repository.CategoryRepository;
import com.tutorial.demo.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produtos com código\n" + id + " não encontrado\n"));
    }

    public Product create(Product produk) {
        if (!StringUtils.hasText(produk.getName())) {
            throw new BadRequestException("Qualquer produto não pode estar vazio");
        }

        if (produk.getCategory() == null) {
            throw new BadRequestException("A categoria não pode ficar vazia");
        }

        if (!StringUtils.hasText(produk.getCategory().getId())) {
            throw new BadRequestException("O ID da categoria não pode ficar vazio\n");
        }

        categoryRepository.findById(produk.getCategory().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Categoria ID " + produk.getCategory().getId() + " não encontrado no banco de dados\n"));

        produk.setId(UUID.randomUUID().toString());
        return productRepository.save(produk);
    }

    public Product edit(Product produk) {
        if (!StringUtils.hasText(produk.getId())) {
            throw new BadRequestException("O ID do produto é obrigatório\n");
        }

        if (!StringUtils.hasText(produk.getName())) {
            throw new BadRequestException("O nome do produto não pode ficar vazio\n");
        }

        if (produk.getCategory() == null) {
            throw new BadRequestException("A categoria não pode ficar vazia\n");
        }

        if (!StringUtils.hasText(produk.getCategory().getId())) {
            throw new BadRequestException("O ID da categoria não pode ficar vazio\n");
        }

        categoryRepository.findById(produk.getCategory().getId())
                .orElseThrow(() -> new BadRequestException(
                        "Categoria ID " + produk.getCategory().getId() + " não encontrado no banco de dados\n"));

        return productRepository.save(produk);
    }

    public Product ubahphoto(String id, String photo) {
        Product produk = findById(id);
        produk.setPhoto(photo);
        return productRepository.save(produk);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}