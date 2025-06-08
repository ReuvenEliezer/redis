package com.reuven.redis.services;

import com.reuven.redis.dto.UserViewModel;
import com.reuven.redis.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User save(UserViewModel userViewModel);

    Optional<User> getById(UUID uuid);

    List<User> findAll();
}
