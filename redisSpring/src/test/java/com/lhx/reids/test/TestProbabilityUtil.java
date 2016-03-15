package com.lhx.reids.test;

import com.lhx.util.ProbabilityUtil;
import org.junit.Test;

import java.util.Random;

/**
 * Created by lhx on 15-9-11 下午2:50
 *
 * @Description
 */
public class TestProbabilityUtil {

    @Test
    public void test1(){
        int n0 = 0 ;
        int n1 = 0 ;
        int n2 = 0 ;
        int n3 = 0 ;
        int n4 = 0 ;
        int n5 = 0 ;
        int n6 = 0 ;
        for (int i = 0; i < 100; i++) {
            short s = ProbabilityUtil.getCharacter("0,5,0,5,20,20,50");
            switch (s) {
                case 0 :
                    n0 ++ ;
                    break;
                case 1 :
                    n1 ++ ;
                    break;
                case 2 :
                    n2 ++ ;
                    break;
                case 3 :
                    n3 ++ ;
                    break;
                case 4 :
                    n4 ++ ;
                    break;
                case 5 :
                    n5 ++ ;
                    break;
                case 6 :
                    n6 ++ ;
                    break;
            }
        }
        System.out.println(n0);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);
        System.out.println(n6);

    }
    @Test
    public void test2(){
        for (int i = 0; i < 200; i++) {
            int random = (int) (Math.random() * 100 + 1);
            System.out.print(random + " ");
        }
    }

    @Test
    public void test3(){
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 200; i++) {
            int rd = r.nextInt(100);
            System.out.print(rd + " ");
        }
    }

    @Test
    public void test4(){
        double d = Math.random() * 100 ;
        int n = (int) d ;
        System.out.println(d);
        System.out.println(n);
    }



}
