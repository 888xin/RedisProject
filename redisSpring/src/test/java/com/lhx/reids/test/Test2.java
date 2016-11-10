package com.lhx.reids.test;

import com.lhx.util.Achievement;
import com.lhx.util.MissionEnum;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lhx on 16-6-15 下午2:17
 *
 * @Description
 */
public class Test2 {

    @Test
    public void test(){
        for (Achievement achievement : Achievement.values()) {

            System.out.println(achievement.getAchieveId());
        }
    }

    @Test
    public void test2(){
        MissionEnum.A.setId(6);
        System.out.println(MissionEnum.A.getId());
    }

    @Test
    public void test3(){
        int a = 3 ;
        int b = 6 ;
        System.out.println(a^b);
        System.out.println(a&b);
    }

    @Test
    public void test4(){
        Integer a = 3 ;
        Integer b = 6 ;
        System.out.println(a.toString());
        System.out.println(b.toString());
    }

    @Test
    public void test5(){
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher("324234");
        boolean flag = matcher.matches();
        System.out.println(flag);
    }

    @Test
    public void test6(){
        String s = "javajavajava";
        String d = "java";
        Pattern pattern = Pattern.compile(d);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            String t = s.substring(matcher.start(), matcher.end());
            System.out.println(t);
        }
    }

    @Test
    public void test7(){
        long d = 109303064146759680L ;
        System.out.println(d);
    }
}
