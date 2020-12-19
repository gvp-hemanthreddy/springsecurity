package com.hemanth.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class Role {
    private Long id;
    private RoleName name;
    private Set<Permission> permissions;
}
