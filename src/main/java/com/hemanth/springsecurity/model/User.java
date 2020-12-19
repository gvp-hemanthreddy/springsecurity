package com.hemanth.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
}
