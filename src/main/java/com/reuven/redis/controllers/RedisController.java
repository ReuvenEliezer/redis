package com.reuven.redis.controllers;

import com.reuven.redis.dto.UserViewModel;
import com.reuven.redis.entities.User;
import com.reuven.redis.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/redis/users")
public class RedisController {

    private final UserService userService;

    public RedisController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User save(@RequestBody UserViewModel userViewModel) {
        return userService.save(userViewModel);
    }

    @GetMapping("/{uuid}")
    public Optional<User> getById(@PathVariable UUID uuid) {
        return userService.getById(uuid);
    }


    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        userService.deleteAll();
    }

    @DeleteMapping("/{uuid}")
    public void deleteById(@PathVariable UUID uuid) {
        userService.deleteById(uuid);
    }

    @DeleteMapping("/all2")
    public Long deleteAllByTemplate() {
        return userService.deleteAllByRedisTemplate();
    }

}
