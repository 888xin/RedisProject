package com.lhx.reids.test;

import com.lhx.service.BroadcastService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by lhx on 15-9-9 下午3:44
 *
 * @Description
 */
public class TestRedisList {

    private BroadcastService broadcastService ;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        broadcastService = (BroadcastService) context.getBean("broadcastService");
    }

    @Test
    public void testLeft(){
        long l = broadcastService.push("xin",23);
        System.out.println(l);
    }

    @Test
    public void testLeft2(){
        Object o = broadcastService.pop("xin");
        System.out.println(o);
    }

    @Test
    public void testRight(){
        long l = broadcastService.in("xin", 23);
        System.out.println(l);
    }

    @Test
    public void testRight2(){
        Object o = broadcastService.out("xin");
        System.out.println(o);
    }

    @Test
    public void testlength(){
        long length = broadcastService.length("xin");
        System.out.println(length);
    }

    @Test
    public void testrange(){
        List<Object> list = broadcastService.range("xin", 0, 2);
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
