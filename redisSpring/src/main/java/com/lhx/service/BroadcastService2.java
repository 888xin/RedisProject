package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lhx on 15-9-9 下午3:12
 *
 * @Description
 */
@Service
public class BroadcastService2 {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public boolean put(String key, String value, Double d) {
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        return zso.add(key, value, d);
    }

    public Set<String> get(String key, double min, double max, long offset, long count) {
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        return zso.rangeByScore(key, min, max, offset, count);
    }

}
