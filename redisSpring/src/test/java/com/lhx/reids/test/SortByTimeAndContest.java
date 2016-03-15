package com.lhx.reids.test;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by lhx on 16-3-9 上午11:58
 *
 * @Description
 */
public class SortByTimeAndContest implements Comparator<Object> {

    //先按价格排，后按使用类型排，最后按失效时间排
    // |价格|类型|失效时间 把三者拼接成一个long整形的数字
    @Override
    public int compare(Object o1, Object o2) {
        CouponUserResponse th1=(CouponUserResponse)o1;
        CouponUserResponse th2=(CouponUserResponse)o2;
        //失效时间
        long now = new Date().getTime();
        Long inter1 = th1.getEnd_time().getTime() - now;
        String time1 = inter1.toString().substring(4);
        Long inter2 = th2.getEnd_time().getTime() - now;
        String time2 = inter2.toString().substring(4);
        String seed1 = th1.getPrice() + "" + th1.getRange_key() + time1 ;
        String seed2 = th2.getPrice() + "" + th2.getRange_key() + time2 ;

        return Long.valueOf(seed1).compareTo(Long.valueOf(seed2));
    }
}
