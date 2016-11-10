package com.lhx.reids.test;

import com.lhx.service.FrontPageService;
import com.lhx.util.CommerceMath;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lhx on 15-12-1 上午11:32
 *
 * @Description
 */
public class TestNewRedis {

    StringRedisTemplate stringRedisTemplate;
    RedisTemplate redisTemplate;

    @Before
    public void before() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring2.9.xml"});

        stringRedisTemplate = (StringRedisTemplate) context.getBean("stringRedisTemplate");
        redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
    }


    @Test
    public void test12() {
        ValueOperations<String, String> voSrt = stringRedisTemplate.opsForValue();
        //voSrt.set("23", "2");
        //stringRedisTemplate.opsForValue().increment("23", 2);
        //stringRedisTemplate.expire("23",10, TimeUnit.SECONDS);
        //stringRedisTemplate.opsForHash().increment("234", "2", 2);
        //String str = voSrt.get("22");
        //System.out.println(str);
        //System.out.println(stringRedisTemplate.opsForHash().get("234","2"));
        voSrt.increment("111", 2);
        System.out.println(voSrt.get("111"));
    }

    @Test
    public void test1(){

        List<Object> list = new ArrayList<>();
        list.add("sd");
        list.add("sd2");
        list.add("sd3");
        List<Object> result = stringRedisTemplate.opsForHash().multiGet("13", list);
        for (Object o : result) {
            System.out.println(o);
        }

    }




}
