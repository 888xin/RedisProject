package com.lhx.reids.test;

import com.lhx.util.ProbabilityUtil;
import org.junit.Test;

/**
 * Created by lhx on 15-9-11 下午2:50
 *
 * @Description
 */
public class TestProbabilityUtil {

    @Test
    public void test1(){
        for (int i = 0; i < 100; i++) {
            short s = ProbabilityUtil.getCharacter("0,5,0,5,20,20,50");
            System.out.println(s);
        }

    }
    @Test
    public void test2(){
        for (int i = 0; i < 200; i++) {
            int random = (int) (Math.random() * 100 + 1);
            System.out.print(random + " ");
        }
    }

}
