package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by lhx on 15-12-1 上午11:29
 *
 * @Description
 */
@Service
public class FrontPageService {

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

    public Set<String> reverseGet(String key, double min, double max, long offset, long count) {
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        return zso.reverseRangeByScore(key, min, max, offset, count);
    }

    public long remove(String key,Object value){
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        long result = zso.remove(key,value);
        return result;
    }

    public Double score(String key,Object value) {
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        return zso.score(key, value);
    }
}
