package com.tutorial.demo.ecommerce.security.service;

import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicelmpl implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findById(username).
                orElseThrow(()-> new UsernameNotFoundException("username" + username+ "Usuário Obrigatorio"));

        return UserDetailsImpl.build(user);

    }
}
