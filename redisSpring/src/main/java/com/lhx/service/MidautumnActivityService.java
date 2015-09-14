package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by lhx on 15-9-10 下午4:19
 *
 * @Description
 */
@Service
public class MidautumnActivityService {

    // 在Redis数据库中的KEY值
    private static final String ACTIVITY = "mid-autumn-and-national-day-activity";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储用户拥有汉字的记录
     * @param accountId
     * @param statistics 数组下标=0中；=1秋；=2国；=3庆；=4快；=5乐；=6自定义；=7（中秋快乐）；=8（国庆快乐）
     */
    public void put(long accountId, short[] statistics) {
        redisTemplate.opsForHash().put(ACTIVITY, accountId, statistics);
    }

    /**
     * 获取
     * @param accountId
     * @return 数组 数组下标=0中；=1秋；=2国；=3庆；=4快；=5乐；=6自定义；=7（中秋快乐）；=8（国庆快乐）
     */
    public short[] get(long accountId){
        return (short[]) redisTemplate.opsForHash().get(ACTIVITY, accountId);
    }
}
