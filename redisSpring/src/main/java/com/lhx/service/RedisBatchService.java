package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lhx on 15-9-9 下午4:34
 *
 * @Description
 */
@Service
public class RedisBatchService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 批量插入（不关注返回值）
     * 批量向redis中插入H码:key(tableName:hcode) value(pcode)
     * 如果键已存在则返回false,不更新,防止覆盖。使用pipeline批处理方式(不关注返回值)
     *  @param list  一个map代表一行记录,2个key:hcode & pcode。
     *  @param tableName redis中key的值为tableName:hcode  对应value值为pcode。
     *  @return
     */
    public boolean addHcode(final List<Map<String, Object>> list, final String tableName) {
        boolean result = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
                for (Map<String, Object> map : list) {
                    byte[] key  = serializer.serialize(tableName+":"+map.get("hcode").toString());
                    byte[] name = serializer.serialize(map.get("pcode").toString());
                    connection.setNX(key, name);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * 从redis中获取(获取密码日志) rPop从链表尾部弹出(最早的日志)
     * 多线程并发读取日志长度的时候，比如都得到结果是1000条。
     * 当多线程每个都 循环1000次 pop弹出 日志的时候，
     * 由于是多线程一起pop，所以每个线程获得的数组中都会包含 null  甚至有的全是null
     * @return
     */
    public List<String> getLogFromRedis() {

        final RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
        //密码日志的长度
        final Long pwdLogSize=stringRedisTemplate.opsForList().size("getpwdList");

        List<Object> pwdLogList = stringRedisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (int i=0 ;i< pwdLogSize ;i++) {
                    byte[] listName  = serializer.serialize("getpwdList");
                    redisConnection.rPop(listName);
                }
                return null ;
            }
        }, false, true);

        //  去除结果中的null
        ArrayList<String> newList=new ArrayList<String>();
        for (Object o : pwdLogList) {
            if(o!=null)
                newList.add(String.valueOf(o));
        }
        return newList;
    }
}
