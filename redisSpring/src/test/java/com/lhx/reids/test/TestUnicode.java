package com.lhx.reids.test;

import com.lhx.util.UnicodeUtil;
import org.junit.Test;

/**
 * Created by lhx on 15-9-14 上午10:50
 *
 * @Description
 */
public class TestUnicode {

    @Test
    public void test(){
        String str = UnicodeUtil.decodeUnicode("&#19968;&#30452;&#27809;&#26377;&#22899;&#26379;&#21451;&#65292;&#22909;&#31354;&#34394;&#65292;&#22909;&#24819;&#35201;");
        System.out.println(str);
    }
}
