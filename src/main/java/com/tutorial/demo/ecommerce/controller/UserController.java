package com.tutorial.demo.ecommerce.controller;

import java.util.List;

import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.service.UserService;
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
public class UserController {

    @Autowired
    private UserService penggunaService;

    @GetMapping("/users")
    public List<User> findAll() {
        return penggunaService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") String id) {
        return penggunaService.findById(id);
    }

    @PostMapping("/users")
    public User create(@RequestBody User pengguna) {
        return penggunaService.create(pengguna);
    }

    @PutMapping("/users")
    public User edit(@RequestBody User pengguna) {
        return penggunaService.edit(pengguna);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable("id") String id) {
        penggunaService.deleteById(id);
    }

}