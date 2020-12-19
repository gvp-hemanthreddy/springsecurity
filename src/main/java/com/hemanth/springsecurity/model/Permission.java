package com.hemanth.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Permission {
    private Long id;
    private PermissionName name;
}
