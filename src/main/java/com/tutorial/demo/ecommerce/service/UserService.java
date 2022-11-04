package com.tutorial.demo.ecommerce.service;

import java.util.List;

import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.exception.BadRequestException;
import com.tutorial.demo.ecommerce.exception.ResourceNotFoundException;
import com.tutorial.demo.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class UserService {

    @Autowired
    private UserRepository penggunaRepository;

    public User findById(String id) {
        return penggunaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com código\n " + id + " não encontrado\n"));
    }

    public List<User> findAll() {
        return penggunaRepository.findAll();
    }

    public User create(User pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("O nome de usuário deve ser preenchido\n");
        }

        if (penggunaRepository.existsById(pengguna.getId())) {
            throw new BadRequestException("Username " + pengguna.getId() + " Já Registrado");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email deve ser preenchido");
        }

        if (penggunaRepository.existsByEmail(pengguna.getEmail())) {
            throw new BadRequestException("Email " + pengguna.getEmail() + " já registrado");
        }

        pengguna.setIsAktif(true);
        return penggunaRepository.save(pengguna);
    }

    public User edit(User pengguna) {
        if (!StringUtils.hasText(pengguna.getId())) {
            throw new BadRequestException("O nome de usuário deve ser preenchido");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("O nome de Email deve ser preenchido ");
        }

        return penggunaRepository.save(pengguna);
    }

    public void deleteById(String id) {
        penggunaRepository.deleteById(id);
    }
}