package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lhx on 15-9-9 下午3:12
 *
 * @Description
 */
@Service
public class BroadcastService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    //压栈
    public Long push(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    //出栈（出来后就没有了！）
    public Object pop(String key){
        return redisTemplate.opsForList().leftPop(key);
    }

    public Long in(String key, Object value){
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Object out(String key){
        return redisTemplate.opsForList().rightPop(key);
    }

    //长度
    public Long length(String key) {
        return redisTemplate.opsForList().size(key);
    }

    //范围检索
    public List<Object> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    //移除
    public void remove(String key, long i, String value) {
        redisTemplate.opsForList().remove(key, i, value);
    }

    //检索
    public Object index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    //置值
    public void set(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    //裁剪
    public void trim(String key, long start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}
