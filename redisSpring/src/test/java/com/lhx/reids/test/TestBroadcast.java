package com.lhx.reids.test;

import com.lhx.service.BroadcastService2;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

/**
 * Created by lhx on 15-11-27 下午3:57
 *
 * @Description
 */
public class TestBroadcast {

    private BroadcastService2 broadcastService ;


    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        broadcastService = (BroadcastService2) context.getBean("broadcastService2");
    }

    @Test
    public void test1(){
        boolean b = broadcastService.put("li","xin-1",4D);
        System.out.println(b);
    }

    @Test
    public void test2(){
        Set<String> set = broadcastService.get("li",0,Double.MAX_VALUE,0,10);
        System.out.println(set);
    }

}
