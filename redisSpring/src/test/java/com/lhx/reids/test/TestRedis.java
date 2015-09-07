package com.lhx.reids.test;

import com.lhx.domain.User;
import com.lhx.service.LoginAccountService;
import com.lhx.service.UserService;
import com.lhx.util.TimeUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * Created by lhx on 15-9-2 上午11:04
 *
 * @Description
 */
public class TestRedis {

    private UserService userService ;
    private LoginAccountService loginAccountService ;


    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        userService = (UserService) context.getBean("userService");
        loginAccountService = (LoginAccountService) context.getBean("loginAccountService");
    }

    @Test
    public void testInsert(){
        User user = new User("123","xin");
        User user2 = new User("124","xin2");
        User user3 = new User("125","xin3");
        userService.put(user);
        userService.put(user2);
        userService.put(user3);
    }

    @Test
    public void testGet(){
        User user = new User("123");
        User user1 = userService.get(user);
        System.out.println(user1);
    }

    @Test
    public void testgetUsers(){
        Collection<Object> ids = new HashSet<Object>();
        ids.add("123");
        ids.add("124");
        ids.add("125");
        Collection collection = userService.getUsers(ids);
        System.out.println(collection);
    }

    @Test
    public void testInsertlogin(){
        loginAccountService.put2(1234L,new Date());
        loginAccountService.put2(1235L,new Date());
        loginAccountService.put2(1236L,new Date());
    }

    @Test
    public void testGetlogin(){
        String info = loginAccountService.get(100L);
        System.out.println(info);

    }

    @Test
    public void testGetlogin2(){
        String info = loginAccountService.get(123421117L, 60*10);
        System.out.println(info);

    }

    @Test
    public void testGetMulti(){
        Collection<Object> ids = new HashSet<Object>();
        Collection<Object> ids2 = new ArrayList<Object>();
        ids.add(1234L);
        ids2.add(1234L);

        ids.add(1235L);
        ids2.add(1235L);

//        ids.add(1236L);
        ids2.add(1236L);
        Collection objects = loginAccountService.getMulti(ids);
        Collection objects2 = loginAccountService.getMulti(ids2);
        Collection objects3 = loginAccountService.getMulti(new ArrayList<Object>());
        for (Object object : objects) {
            String[] strs = object.toString().split(":");
            System.out.println(strs[0]);
            System.out.println(strs[1]);
            System.out.println(new Date(Long.valueOf(strs[1])));
        }
//        for (Object object : objects2) {
//            System.out.println(object);
//        }
//        for (Object object : objects3) {
//            System.out.println(object);
//        }
    }

    @Test
    public void testgetLoginMap(){
        Collection<Object> ids = new HashSet<Object>();
        ids.add(1234L);
        ids.add(1235L);
        Map<Long, Boolean> map = loginAccountService.getLoginMap(ids,60*10);
        for (Long aLong : map.keySet()) {
            System.out.println(aLong + ":" + map.get(aLong));
        }
    }

    @Test
    public void testTime(){
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.toString());

        String dateStr = TimeUtil.getUtcTimeForDate(date);
        System.out.println(dateStr);

        Date date1 = TimeUtil.getDateByUtcFormattedDate(dateStr);
        System.out.println(date1);
        System.out.println(date1.toString());


    }

    @Test
    public void testTime2() throws InterruptedException {
        Date date = new Date();
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(2000);

        Date date1 = new Date(time);
        System.out.println(date1);

        long l = new Date().getTime()-date.getTime();
        System.out.println(l/1000);
        long day = l/(24*60*60*1000);

        long hour = (l/(60*60*1000)-day*24);

        long min = ((l/(60*1000))-day*24*60-hour*60);

        long s = (l/1000-day*24*60*60-hour*60*60-min*60);
    }

    @Test
    public void testtime3(){
        for (int i = 0; i < 20; i++) {
            long random = (long) (Math.random()*(1000 * 60 * 60 * 24 * 15)) + (10 * 1000);
            long time = new Date().getTime() - random ;
            Date logoutTime = new Date(time);
            String str = TimeUtil.getStandardDateTime(logoutTime);
            System.out.println(str);
        }
//        double d = Math.random()*(1000 * 60 * 60 * 24*30) + (10 * 1000);
//        System.out.println(d);
    }

    @Test
    public void testIter(){
        Collection collection = Arrays.asList("a","b","c","d","e","f");
        for (Object o : collection) {
            if (o.equals("b")){
                System.out.println(o);
                collection.remove(o);

            }
        }
//        for (int i = 0; i < collection.size(); i++) {
//            if (collection.)
//        }
    }
}
