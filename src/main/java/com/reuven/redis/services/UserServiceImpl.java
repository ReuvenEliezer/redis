package com.reuven.redis.services;

import com.reuven.redis.dto.UserViewModel;
import com.reuven.redis.entities.User;
import com.reuven.redis.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserViewModel userViewModel) {
        User user = new User(userViewModel.username(), userViewModel.email());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public List<User> findAll() {
       return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .filter(Objects::nonNull)
                .toList();
    }
}
