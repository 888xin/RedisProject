package com.lhx.reids.test;

import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by lhx on 16-3-9 下午1:16
 *
 * @Description
 */
public class Test1 {

    public static void main(String[] args) {
        new Test1().getCouponsOdds();
    }

    public void getCouponsOdds(){
        //查询该用户所有龙筹 分两种（1进入 赛事 查 普通/（足球或篮球）赛事 2进入押押，查 普通/押押）
        //CouponUserResponse(Long user_id, Long coupon_id, Integer price, Integer range_key, Date end_time)
        //range_key两位数 默认1开头 range_key = 10 赛事2开头 range_key=21，22 联赛3开头 range_key = 31，32  押押4开头 range_key=40
        CouponUserResponse coupon1 = new CouponUserResponse(1L,2L,5,21,new Date(1457500389199L));
        CouponUserResponse coupon2 = new CouponUserResponse(1L,2L,50,12,new Date(1457500389100L));
        CouponUserResponse coupon3 = new CouponUserResponse(1L,2L,100,11,new Date(1457500389198L));
        CouponUserResponse coupon4 = new CouponUserResponse(1L,2L,10,11,new Date(1457500389299L));
        CouponUserResponse coupon5 = new CouponUserResponse(1L,2L,5,22,new Date(1457500384199L));
        CouponUserResponse coupon6 = new CouponUserResponse(1L,2L,10,21,new Date(1457500385199L));
        CouponUserResponse coupon7 = new CouponUserResponse(1L,2L,15,12,new Date(1457500389699L));
        //放入排序集合中
        SortedSet<CouponUserResponse> set = new TreeSet<CouponUserResponse>(new SortByTimeAndContest());
        set.add(coupon1);
        set.add(coupon2);
        set.add(coupon3);
        set.add(coupon4);
        set.add(coupon5);
        set.add(coupon6);
        set.add(coupon7);
        for (CouponUserResponse couponUserResponse : set) {
            System.out.println(couponUserResponse);
        }
    }

    //重写排序规则
    class SortByTimeAndContest implements Comparator<Object> {
        //先按价格排，后按使用类型排，最后按失效时间排（这个可以任意调整）
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
            String seed1 = th1.getPrice() + "" + th1.getRange_key()+""+ time1 ;
            String seed2 = th2.getPrice() + "" + th2.getRange_key() + time2 ;

            return Long.valueOf(seed1).compareTo(Long.valueOf(seed2));
        }
    }


}
