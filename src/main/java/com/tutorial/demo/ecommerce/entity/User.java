package com.tutorial.demo.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private String id;

    private String password;
    private String name;
    @JsonIgnore
    private String address;
    private String email;
    @JsonIgnore
    private String hp;

    private String roles;
    @JsonIgnore
    private Boolean isAktif;

    public User(String username) {
        this.id = username;
    }

}

