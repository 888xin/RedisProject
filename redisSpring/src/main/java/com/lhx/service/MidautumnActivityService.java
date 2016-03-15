package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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
    org.springframework.data.redis.core.StringRedisTemplate redisTemplate;

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
        short[] result = (short[]) redisTemplate.opsForHash().get(ACTIVITY, accountId);
        if (result == null){
            result = new short[9];
        }
        return result ;
    }

    public boolean increment(Long accountId, int index, long count) {
        return redisTemplate.opsForHash().increment(ACTIVITY+accountId, index+"", count) > 0;
    }

    public Object getOne(Long accountId, int index){
        return redisTemplate.opsForHash().get(ACTIVITY+accountId, index+"");
    }

    public List<Object> getMulti(Long accountId){
        List<Object> list = new ArrayList<Object>();
        list.add("0");
        list.add("1");
        return redisTemplate.opsForHash().multiGet(ACTIVITY+accountId, list);
    }

    public Collection<Object> getOne2(long accountId){
        List<Object> list = new ArrayList<Object>();
        list.add("0");
        list.add("1");
        Collection<Object> list1 = redisTemplate.opsForHash().multiGet(ACTIVITY + accountId, list);
        return list1 ;
    }

    public Boolean addCharacter(Long accountId, int index, int count) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        return ops.increment(buildRedisKey(accountId), index+"", count) > 0;
    }

    private String buildRedisKey(Long accountId) {
        return "nyx:notifiy:count:" + accountId;
    }

    public Map<Integer, Integer> getIndex(Long accountId) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        Map<String, String> ret = ops.entries(buildRedisKey(accountId));
        Map<Integer, Integer> respMap = new HashMap<Integer, Integer>();
        if (ret != null) {
            Iterator<String> it = ret.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = ret.get(key);
                respMap.put(Integer.valueOf(key), Integer.valueOf(value));
            }
        }
        return respMap;
    }


}
