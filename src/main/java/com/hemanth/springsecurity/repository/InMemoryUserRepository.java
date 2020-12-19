package com.hemanth.springsecurity.repository;

import com.google.common.collect.Sets;
import com.hemanth.springsecurity.model.Permission;
import com.hemanth.springsecurity.model.PermissionName;
import com.hemanth.springsecurity.model.Role;
import com.hemanth.springsecurity.model.RoleName;
import com.hemanth.springsecurity.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class InMemoryUserRepository implements UserRepository {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUserName(String name) {
        return getUsers().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    private List<User> getUsers() {
        Permission studentRead = new Permission(1L, PermissionName.STUDENT_READ);
        Permission studentWrite = new Permission(2L, PermissionName.STUDENT_WRITE);
        Permission courseRead = new Permission(3L, PermissionName.COURSE_READ);
        Permission courseWrite = new Permission(4L, PermissionName.COURSE_WRITE);

        Set<Permission> studentPermissions = Sets.newHashSet(studentRead, studentWrite);
        Set<Permission> adminPermissions = Sets.newHashSet(studentRead, studentWrite, courseRead, courseWrite);
        Set<Permission> adminTraineePermissions = Sets.newHashSet(studentRead, courseRead);

        Role studentRole = new Role(1L, RoleName.STUDENT, studentPermissions);
        Role adminRole = new Role(2L, RoleName.ADMIN, adminPermissions);
        Role adminTraineeRole = new Role(3L, RoleName.ADMINTRAINEE, adminTraineePermissions);

        User user = new User(
                1L,
                "user",
                "user",
                "user@gmail.com",
                passwordEncoder.encode("user"),
                Sets.newHashSet(studentRole)
        );
        User admin = new User(
                2L,
                "admin",
                "admin",
                "admin@gmail.com",
                passwordEncoder.encode("admin"),
                Sets.newHashSet(adminRole)
        );
        User admintrainee = new User(
                3L,
                "admintrainee",
                "admintrainee",
                "admintrainee@gmail.com",
                passwordEncoder.encode("admintrainee"),
                Sets.newHashSet(adminTraineeRole)
        );

        return Arrays.asList(user, admin, admintrainee);

        /*
        UserDetails userDetails = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                //.roles(STUDENT.name()) //ROLE_STUDENT
                .authorities(STUDENT.getAuthorities())
                .build();

        UserDetails adminDetails = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                //.roles(ADMIN.name()) //ROLE_ADMIN
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails adminTraineeDetails = User.builder()
                .username("admintrainee")
                .password(passwordEncoder.encode("admintrainee"))
                //.roles(ADMINTRAINEE.name()) //ROLE_ADMINTRAINEE
                .authorities(ADMINTRAINEE.getAuthorities())
                .build();*/
    }
}
