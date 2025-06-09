package com.reuven.redis.services;

import com.reuven.redis.dto.UserViewModel;
import com.reuven.redis.entities.User;
import com.reuven.redis.repositories.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;

    public UserServiceImpl(UserRepository userRepository, RedisTemplate<String, User> redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User save(UserViewModel userViewModel) {
        User user = new User(userViewModel.username(), userViewModel.email());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getById(UUID uuid) {
        return userRepository.findById(uuid.toString());
    }

    @Override
    public List<User> findAll() {
       return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid.toString());
    }

    @Override
    public Long deleteAllByRedisTemplate() {
        Set<String> keys = redisTemplate.keys(User.class.getSimpleName().toLowerCase() + ":*");
        return redisTemplate.delete(keys);
    }
}
