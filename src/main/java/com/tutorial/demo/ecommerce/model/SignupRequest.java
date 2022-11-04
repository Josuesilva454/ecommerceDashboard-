package com.tutorial.demo.ecommerce.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignupRequest implements Serializable {

    private String username;
    private String password;
    private String email;
    private String name;

}