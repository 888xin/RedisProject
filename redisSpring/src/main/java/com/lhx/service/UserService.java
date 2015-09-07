package com.lhx.service;

import com.lhx.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by lhx on 15-9-2 上午11:02
 *
 * @Description
 */
@Service("userService")
public class UserService {

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    public void put(User user) {
        redisTemplate.opsForHash().put(user.getObjectKey(), user.getKey(), user);
    }

    public void delete(User key) {
        redisTemplate.opsForHash().delete(key.getObjectKey(), key.getKey());
    }

    public User get(User key) {
        return (User) redisTemplate.opsForHash().get(key.getObjectKey(), key.getKey());
    }

    public Collection getUsers(Collection<Object> ids){
        Collection<Object> collection = redisTemplate.opsForHash().multiGet("USER", ids);
        return collection ;
    }
}
