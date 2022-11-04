package com.tutorial.demo.ecommerce.controller;

import com.tutorial.demo.ecommerce.entity.User;
import com.tutorial.demo.ecommerce.model.JwtResponse;
import com.tutorial.demo.ecommerce.model.LoginRequest;
import com.tutorial.demo.ecommerce.model.RefreshTokenRequest;
import com.tutorial.demo.ecommerce.model.SignupRequest;
import com.tutorial.demo.ecommerce.security.jwt.JwtUtils;
import com.tutorial.demo.ecommerce.security.service.UserDetailsImpl;
import com.tutorial.demo.ecommerce.security.service.UserDetailsServicelmpl;
import com.tutorial.demo.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServicelmpl userDetailsServiceImpl;
    @Autowired
     JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token =  jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok()
                .body(new JwtResponse(token, refreshToken, principal.getUsername(), principal.getEmail()));
    }


    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        User user = new User();
        user.setId(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRoles("user");
        User created = userService.create(user);
        return created;
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        boolean valid = jwtUtils.validateJwtToken(token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
            String username = jwtUtils.getUserNameFromJwtToken(token);
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null,
                    userDetailsImpl.getAuthorities());
            String newToken = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
            return ResponseEntity.ok(new JwtResponse(newToken, refreshToken, username, userDetailsImpl.getEmail()));
        }
    }
