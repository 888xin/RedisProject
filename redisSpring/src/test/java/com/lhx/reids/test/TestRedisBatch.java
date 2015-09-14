package com.lhx.reids.test;

import com.lhx.service.RedisBatchService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhx on 15-9-9 下午5:21
 *
 * @Description
 */
public class TestRedisBatch {

    private RedisBatchService redisBatchService ;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        redisBatchService = (RedisBatchService) context.getBean("redisBatchService");
    }

    @Test
    public void testInsert(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hcode","12321321321");
        map.put("pcode","12312321312321");
        list.add(map);
        boolean l = redisBatchService.addHcode(list,"lifeix");
        System.out.println(l);
    }

    @Test
    public void testGet(){
        List<String> list = redisBatchService.getLogFromRedis();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
