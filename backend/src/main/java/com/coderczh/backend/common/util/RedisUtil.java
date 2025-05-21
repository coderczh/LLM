package com.coderczh.backend.common.util;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void addStr(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getStr(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void setStr(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }
}
