package com.hemanth.springsecurity.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static com.hemanth.springsecurity.model.PermissionName.*;

@AllArgsConstructor
public enum RoleName {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));

    @Getter
    private final Set<PermissionName> permissions;
}
