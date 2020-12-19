package com.hemanth.springsecurity.repository;

import com.hemanth.springsecurity.model.User;

import java.util.Optional;

public interface UserRepository {
    public Optional<User> findByUserName(String name);
}
