package com.lhx.reids.test;

import com.lhx.activity.MidautumnActivity;
import com.lhx.service.MidautumnActivityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lhx on 15-9-15 下午4:50
 *
 * @Description
 */
public class TestRedisActivity {

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
    public void test1(){
        short[] shorts = midautumnActivityService.get(2L);
        for (short aShort : shorts) {
            System.out.println(aShort);
        }
    }
}
