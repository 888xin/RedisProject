package com.lhx.reids.test;

import com.lhx.activity.MidautumnActivity;
import com.lhx.service.MidautumnActivityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by lhx on 15-9-10 下午4:24
 *
 * @Description
 */
public class TestActivity {

    private MidautumnActivityService midautumnActivityService ;
    private MidautumnActivity midautumnActivity ;


    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        midautumnActivityService = (MidautumnActivityService) context.getBean("midautumnActivityService");
        midautumnActivity = (MidautumnActivity) context.getBean("midautumnActivity");
    }

    @Test
    public void test(){
        //short的取值范围为-32768~32767
        short[] c = new short[20];
        c[0] = 32767;
        System.out.println(c[0]);
        System.out.println(c[1]);
    }


    @Test
    public void test2(){
        short[] c = {0,1,0,1,0,0,0};
        midautumnActivityService.put(12L,c);
    }

    @Test
    public void test3(){
        short[] c = midautumnActivityService.get(12L);
        for (short i : c) {
            System.out.print(i + " ");
        }
    }

    //掉汉字 测试 送礼
    @Test
    public void testinsert1(){
        //情人玫瑰 "0,5,0,5,20,20,50,0" type=1
        short s1 = midautumnActivity.insert(1L, "0,5,0,5,20,20,50,0", 1);
        System.out.println(s1);
        //月饼/便便 "0,10,0,10,25,25,30,0" type=1
        short s2 = midautumnActivity.insert(1L, "0,10,0,10,25,25,30,0", 1);
        System.out.println(s2);
        //小于10块的礼物 "3,15,1,15,30,30,6,0" type=1
        short s3 = midautumnActivity.insert(1L, "3,15,1,15,30,30,6,0", 1);
        System.out.println(s3);
        //10-49块的礼物 "10,20,5,20,22,22,1,0" type=1
        short s4 = midautumnActivity.insert(1L, "10,20,5,20,22,22,1,0", 1);
        System.out.println(s4);
        //50-99块的礼物 "40,12,24,12,6,6,0,0" type=1
        short s5 = midautumnActivity.insert(1L, "40,12,24,12,6,6,0,0", 1);
        System.out.println(s5);
        //100-200块的礼物 "40,0,60,0,0,0,0,0" type=1
        short s6 = midautumnActivity.insert(1L, "40,0,60,0,0,0,0,0", 1);
        System.out.println(s6);
        //300块的礼物 "0,0,0,0,0,0,0,100" type=1
        short s7 = midautumnActivity.insert(1L, "0,0,0,0,0,0,0,100", 1);
        System.out.println(s7);
    }

    //掉汉字 测试 充值（android）
    @Test
    public void testinsert2(){
        //6 "1,20,0,20,34,35,0" type=2
        short s1 = midautumnActivity.insert(1L, "1,20,0,20,34,35,0", 2);
        System.out.println(s1);
        //10(首冲) "3,20,1,20,28,28,0" type=2
        short s2 = midautumnActivity.insert(1L, "3,20,1,20,28,28,0", 2);
        System.out.println(s2);
        //18 "10,16,8,16,25,25,0" type=2
        short s3 = midautumnActivity.insert(1L, "10,16,8,16,25,25,0", 2);
        System.out.println(s3);
        //68 "40,12,24,12,6,6,0" type=2
        short s4 = midautumnActivity.insert(1L, "40,12,24,12,6,6,0", 2);
        System.out.println(s4);
        //168 "40,0,60,0,0,0,0" type=2
        short s5 = midautumnActivity.insert(1L, "40,0,60,0,0,0,0", 2);
        System.out.println(s5);
        //388 "0,0,0,0,0,0,100" type=2
        short s6 = midautumnActivity.insert(1L, "0,0,0,0,0,0,100", 2);
        System.out.println(s6);
        //648 "0,0,0,0,0,0,100" type=2
        short s7 = midautumnActivity.insert(1L, "0,0,0,0,0,0,100", 2);
        System.out.println(s7);
    }

    //掉汉字 测试 充值（IOS）
    @Test
    public void testinsert3(){
        //18 "10,16,8,16,25,25" type=3
        short s1 = midautumnActivity.insert(1L, "10,16,8,16,25,25", 3);
        System.out.println(s1);
        //50 "40,12,24,12,6,6" type=3
        short s2 = midautumnActivity.insert(1L, "40,12,24,12,6,6", 3);
        System.out.println(s2);
        //98 "45,7,30,8,5,5" type=3
        short s3 = midautumnActivity.insert(1L, "45,7,30,8,5,5", 3);
        System.out.println(s3);
        //168 "40,0,60,0,0,0" type=3
        short s4 = midautumnActivity.insert(1L, "40,0,60,0,0,0", 3);
        System.out.println(s4);
    }

    @Test
    public void testconsume(){
        boolean flag = midautumnActivity.consume(1L,3,null);
        System.out.println(flag);
    }

    @Test
    public void testGet(){
        short[] s = midautumnActivity.get(1L);
        for (short i : s) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void testgetMulti(){
        List<Object> n = midautumnActivityService.getMulti(14L);
        System.out.println(n);
    }

    @Test
    public void test4(){
        boolean l = midautumnActivityService.increment(14L, 0, 1L);
        System.out.println(l);
    }

    @Test
    public void test5(){
        Object n = midautumnActivityService.getOne(12L, 0);
        System.out.println(n);
    }

    @Test
    public void test6(){
        Collection<Object> n = midautumnActivityService.getOne2(12L);
        System.out.println(n);
    }

    @Test
    public void testaddCharacter(){
        midautumnActivityService.addCharacter(3L,3,1);
    }

    @Test
    public void testgetIndex(){
        Map<Integer, Integer> map = midautumnActivityService.getIndex(3L);
        System.out.println(map);
    }

}
