package com.lhx.reids.test;

import com.lhx.service.LoginAccountService;
import com.lhx.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * Created by lhx on 15-9-6 上午10:44
 *
 * @Description
 */
public class TestRedis2 {


    private LoginAccountService loginAccountService ;


    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        loginAccountService = (LoginAccountService) context.getBean("loginAccountService");
    }

    @Test
    public void testInsert(){
        for (int i = 0; i < 100000; i++) {
            loginAccountService.put(i);
        }

    }

    @Test
    public void testInsert2(){
        loginAccountService.put(1L);
        loginAccountService.put(2L);
        loginAccountService.put(3L);
        loginAccountService.put(4L);
        loginAccountService.put(5L);
        loginAccountService.put(6L);
        loginAccountService.put(7L);
        loginAccountService.put(8L);
        loginAccountService.put(9L);

    }

    @Test
    public void testGet(){
        Collection<Object> set = new HashSet<Object>();
        set.add(1L);
        set.add(9L);
        set.add(18L);
        set.add(4L);
        set.add(7L);
        set.add(10L);
        Map<Long, Boolean> map = loginAccountService.getLoginMap(set, 60*10);
        for (Long aLong : map.keySet()) {
            System.out.println(aLong + ":" + map.get(aLong));
        }
    }

    @Test
    public void testOne(){
        String str = loginAccountService.get(2);
        System.out.println(str);
    }

    @Test
    public void testgetMulti(){
        Collection<Object> list = new ArrayList<Object>();
        list.add(1L);
        list.add(9L);
        list.add(2222222L);
        list.add(4L);
        list.add(7L);
        list.add(333333L);
        Collection collection = loginAccountService.getMulti(list);
        for (Object o : collection) {
            System.out.println(o);
        }
    }

    @Test
    public void testOneAndTime(){
        String str = loginAccountService.get(2, 60*10);
        System.out.println(str);
    }

    @Test
    public void testNull(){
//        Integer n = null ;
//        int i = 1 ;
//        System.out.println(n.equals(i));
//        long a = 5;
//        long b = 3 ;
//        System.out.println(a / b);
        System.out.println(new Date().getTime());
        System.out.println((1441617191464L - 1440617191464L)/(1000*60*60));
    }

    @Test
    public void testget2time(){
        String result = loginAccountService.get2(2L,60);
        System.out.println(result);
    }

    @Test
    public void testLongTime(){
        long random = (long) (Math.random()*(1000 * 60 * 60 * 24 * 8)) + (1000 * 60 * 60 * 24 * 7);
        long currentTime = new Date().getTime();
        long time = currentTime - random ;
        System.out.println(time);
    }

    @Test
    public void testTime2(){
        int i = 1000 * 60 * 60 * 24 * 7 ;
        System.out.println(i);
    }

    @Test
    public void testInsert3(){
        loginAccountService.put5(1L);
        loginAccountService.put5(2L);
        loginAccountService.put5(3L);
        loginAccountService.put5(4L);
        loginAccountService.put5(5L);
        loginAccountService.put5(6L);
        loginAccountService.put5(7L);
        loginAccountService.put5(8L);
        loginAccountService.put5(9L);

    }

    @Test
    public void testdelete2(){
        loginAccountService.put5(1L);
        loginAccountService.delete(2L);
        loginAccountService.put5(3L);
        loginAccountService.delete(4L);
        loginAccountService.put5(5L);
        loginAccountService.delete(6L);
        loginAccountService.delete(7L);
        loginAccountService.put5(8L);
        loginAccountService.put5(9L);

    }

    @Test
    public void testgetLoginStatusMap3(){
        Collection<Object> list = new ArrayList<Object>();
        list.add(1L);
        list.add(9L);
        list.add(2222222L);
        list.add(4L);
        list.add(7L);
        list.add(333333L);
        Map<Long, Boolean> map = loginAccountService.getLoginStatusMap3(list, 60*20);
        for (Long aLong : map.keySet()) {
            System.out.println(aLong + ":" + map.get(aLong));
        }
    }
}
