package com.reuven.redis.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RedisHash(value = "user")//, timeToLive = 10) // TTL in seconds
public record User(@Id String uuid,
                   String name,
                   String email,
                   @TimeToLive(unit = TimeUnit.SECONDS) Long ttl
) {
    public User(String name, String email) {
        this(UUID.randomUUID().toString(), name, email, Duration.ofSeconds(10).getSeconds());
    }

}
