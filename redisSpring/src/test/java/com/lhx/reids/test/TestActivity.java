package com.lhx.reids.test;

import com.lhx.activity.MidautumnActivity;
import com.lhx.service.MidautumnActivityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

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

    @Test
    public void test7(){
        System.out.println(60*60*24*115);
        System.out.println(60*60*24*7);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void test8(){
        long date = new Date().getTime();
        System.out.println(date);
        long time = date / 10000 ;
        System.out.println(time);

        double timeDouble =  time % 1000_0000L ;
        System.out.println(timeDouble);

        long l1= 1460614252605L ;
        Calendar calendar = Calendar.getInstance();
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test9(){
        String result="";
        for(int i=0;i<6;i++){
            int intVal=(int)(Math.random()*26+97);
            result=result+(char)intVal;
        }
        System.out.println(result);
    }

    @Test
    public void test10(){
        String result = "";
        for (int i = 0; i < 6; i++) {
//            int intVal = (int) (Math.random() * 26 + 97);
            int intVal = (int) (Math.random() * 10);
            result += intVal;
        }
        System.out.println(result);
    }

    @Test
    public void test11(){

        Map<Integer, Integer> map =  backGold(90);
        for (Integer integer : map.keySet()) {
            System.out.println(integer + ":" + map.get(integer));
        }

    }

    private Map<Integer, Integer> backGold(double back){
        Map<Integer, Integer> ret = new HashMap<>();
        if (back > 100) { // 超过100的部分全部以100返回
            int num = (int) (back / 100);
            ret.put(100, num);
            back = back % 100;
        }
        if (back > 95) { // 大于95按照100取整
            Integer num = ret.get(100);
            if (num == null) {
                num = 0;
            }
            ret.put(100, num + 1);
        } else {
            if (back >= 50) { // 大于50 获取50元卷
                ret.put(50, 1);
                back = back % 50;
            }
            if (back >= 20) { // 大于20 获取20元卷
                int num = (int) (back / 20);
                ret.put(20, num);
                back = back % 20;
            }
            if (back >= 10) {// 大于10 获取10元卷
                ret.put(10, 1);
                back = back % 10;

            }
            if (back > 5) {// 大于5获取10元卷
                Integer num = ret.get(10);
                if (num == null) {
                    num = 0;
                }
                ret.put(10, num + 1);
            } else if (back > 0){
                ret.put(5, 1);
            }
        }
        return ret;

    }

    @Test
    public void test12(){

        int backGold = 90 ;
        if (backGold > 0) {
            // 派奖
            if (backGold % 60 == 0){
                int num = (backGold / 60)*3 ;
                System.out.println(backGold);
                System.out.println(num);
            } else if (backGold % 30 == 0){
                int num = (backGold / 30)*3 ;
                System.out.println(backGold);
                System.out.println(num);
            } else if (backGold % 15 == 0){
                int num = (backGold / 15)*3 ;
                System.out.println(backGold);
                System.out.println(num);
            } else {
                System.out.println("");
            }
        }
    }

    @Test
    public void test13(){

        Integer num = 2 ;
        num = (num == null) ? 1 : num ;
        System.out.println(num);
    }

    @Test
    public void test14(){

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, 4);
        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.SECOND, 5);
            System.out.println(calendar.getTime());
        }

    }

    @Test
    public void test15(){

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int n = random.nextInt(500);
            System.out.println(n);
        }


    }

    @Test
    public void test16(){

        Integer n = null ;
        System.out.println(9 == n);


    }

    @Test
    public void test17(){

        String f = "%0" + 5 + "d";
        System.out.println(f);
        System.out.println( String.format(f, 2014));


    }

    @Test
    public void test18(){

        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR)).substring(2, 4);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        String key = year + String.format("%02d",month) +String.format("%02d",day);

        System.out.println(key);


    }

    @Test
    public void test19() throws InterruptedException {
//        long date = new Date().getTime();
//        System.out.println(date);
//        long time = date / 1000 ;
//        System.out.println(time);
//
//        double timeDouble =  time % 1000_0000L ;
//        System.out.println(timeDouble);
//
//        long l1= 1460614252605L ;
//        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis() / 1000);
            Thread.sleep(1000);
        }

    }

    @Test
    public void test20() throws InterruptedException {

        Long l = 100L ;
        Integer n = 23 ;
        String key = String.format(this.getClass().getName() + ":%d:%d:",l,n);
        System.out.println(key);

    }

    @Test
    public void test21(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        System.out.println(calendar.getTimeInMillis());


    }
}
