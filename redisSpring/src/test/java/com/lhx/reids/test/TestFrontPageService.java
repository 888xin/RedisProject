package com.lhx.reids.test;

import com.lhx.service.FrontPageService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lhx on 15-12-1 上午11:32
 *
 * @Description
 */
public class TestFrontPageService {

    private FrontPageService frontPageService;

    StringRedisTemplate stringRedisTemplate;
    RedisTemplate redisTemplate;


    @Before
    public void before() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});

        frontPageService = (FrontPageService) context.getBean("frontPageService");
        stringRedisTemplate = (StringRedisTemplate) context.getBean("stringRedisTemplate");
        redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
    }

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            frontPageService.put("xin", "xin-" + i, (double) i);
        }
    }

    @Test
    public void test2() {
        Set<String> set = frontPageService.get("xin", 0, Double.MAX_VALUE, 0, 10);
        System.out.println(set);
    }

    @Test
    public void test3() {
        Set<String> set = frontPageService.reverseGet("xin", 0, Double.MAX_VALUE, 0, 10);
        System.out.println(set);
    }

    @Test
    public void test4() {
        long set = frontPageService.remove("xin", "xin-5");
        System.out.println(set);
    }

    @Test
    public void test5() {

        System.out.println(null + "sd");
    }

    @Test
    public void test6() {
        for (int i = 0; i < 10; i++) {
            frontPageService.put("xin2", i + "", (double) i);
        }
    }

    @Test
    public void test7() {
        Set<String> set = frontPageService.get("xin2", -2, Double.MAX_VALUE, 0, 20);
        System.out.println(set);
    }

    @Test
    public void test8() {
        long set = frontPageService.remove("xin2", 4 + "");
        System.out.println(set);
    }

    @Test
    public void test9() {
        Double set = frontPageService.score("xin2", 14 + "");
        System.out.println(set);
    }

    @Test
    public void test10() {
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        //zso.add("xin2",-1+"",-1);
        zso.removeRange("xin2", 2, 3);
    }

    @Test
    public void test11() {
        System.out.println((int) Math.ceil(Math.random() * 200));
    }

    @Test
    public void test12() {
        ValueOperations<String, String> voSrt = stringRedisTemplate.opsForValue();
        //voSrt.set("23", "2");
        //stringRedisTemplate.opsForValue().increment("23", 2);
        //stringRedisTemplate.expire("23",10, TimeUnit.SECONDS);
        //stringRedisTemplate.opsForHash().increment("234", "2", 2);
        //String str = voSrt.get("22");
        //System.out.println(str);
        //System.out.println(stringRedisTemplate.opsForHash().get("234","2"));
        voSrt.increment("111", 2);
        System.out.println(voSrt.get("111"));
    }

    @Test
    public void test13() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        int n = 4;
        int no1 = 3;
        int no2 = 4;
        int no3 = 5;
        if (n > stack.peek()) {
            stack.pop();
            if (n > stack.peek()) {
                int med = stack.pop();
                if (n > stack.peek()) {
                    int max = stack.pop();
                    stack.push(n);
                    stack.push(max);
                    stack.push(med);
                } else {
                    stack.push(n);
                    stack.push(med);
                }
            } else {
                stack.push(n);
            }
        }
    }

    @Test
    public void test14() {

        //String[] strings = {"2","4","2","6","2","5","9","2","6","1","9","2"};
        String[] strings = {null, null, null, null, null, null, null, null, null, null, null, null};

        Stack<Integer> stack = new Stack<Integer>();
        int no1 = 0;
        int no2 = 0;
        int no3 = 0;
        for (int i = 0; i < 12; i++) {
            String tmp = strings[i];
            switch (i) {
                case 0:
                    no1 = i + 1;
                    if (tmp != null) {
                        stack.push(Integer.valueOf(tmp));
                    } else {
                        stack.push(1);
                    }
                    break;
                case 1:
                    no2 = i + 1;
                    if (tmp != null) {
                        stack.push(Integer.valueOf(tmp));
                    } else {
                        stack.push(1);
                    }
                    break;
                case 2:
                    no3 = i + 1;
                    if (tmp != null) {
                        stack.push(Integer.valueOf(tmp));
                    } else {
                        stack.push(1);
                    }
                    break;
                default:
                    if (tmp != null) {
                        int n = Integer.valueOf(tmp);
                        if (n > stack.peek()) {
                            stack.pop();
                            if (n > stack.peek()) {
                                int med = stack.pop();
                                if (n > stack.peek()) {
                                    int max = stack.pop();
                                    stack.push(n);
                                    stack.push(max);
                                    stack.push(med);
                                    no1 = no2;
                                    no2 = no3;
                                    no3 = i + 1;
                                } else {
                                    stack.push(n);
                                    stack.push(med);
                                    no1 = no2;
                                    no2 = i + 1;
                                }
                            } else {
                                stack.push(n);
                                no1 = i + 1;
                            }
                        }
                    }
                    break;
            }

        }
        int result1 = stack.pop();
        int result2 = stack.pop();
        int result3 = stack.pop();
        Object[] set3 = {no1 + "," + result1, no2 + "," + result2, no3 + "," + result3};
        for (Object o : set3) {
            System.out.println(o);
        }
    }

    @Test
    public void test15() {
        int num = 14;
        ZSetOperations<String, String> zso = stringRedisTemplate.opsForZSet();
        zso.add("gg", "w*w3中了" + (int) (23.0) + "龙筹", Double.parseDouble("23423"));
        zso.add("gg", "w*w3中了" + (int) (23.0) + "龙筹", Double.parseDouble("23424"));
        zso.add("gg", "w*w3中了" + (int) (23.0) + "龙筹", Double.parseDouble("23425"));
        zso.add("gg", "w*w3中了" + (int) (23.0) + "龙筹", Double.parseDouble("23426"));
//        zso.add("gg", "w*w2中了" + (int)(23.0)+ "龙筹", Double.parseDouble("23422"));
//        zso.add("gg", "w*w6中了" + (int)(23.0)+ "龙筹", Double.parseDouble("23426"));
//        zso.add("gg", "w*w7中了" + (int)(23.0)+ "龙筹", Double.parseDouble("23427"));
//        zso.add("gg", "w*w8中了" + (int)(23.0)+ "龙筹", Double.parseDouble("23428"));
        Set<String> sets = zso.reverseRangeByScore("gg", 0, Double.MAX_VALUE, 0, num);
        for (String set : sets) {
            System.out.println(set);
        }
    }

    @Test
    public void test16() {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
//        hashOperations.put("mm", "2015-12-23", "34");
//        hashOperations.put("mm", "2015-12-22", "32");
//        hashOperations.put("mm", "2015-12-21", "31");
//        hashOperations.delete("mm","2015-12-23");
//        List<String> list = Arrays.asList("2015-12-23", "2015-12-22", "2015-12-21");
//        List<String> result = hashOperations.multiGet("mm", list);
//        for (String s : result) {
//            System.out.println(s);
//        }
        System.out.println(hashOperations.size("mm"));
    }

    public static String getCbsTimeDiff(Date createTime) {
        if (createTime == null) {
            return "刚刚";
        }
        Date now = new Date();
        long diff = now.getTime() - createTime.getTime();
        long minutes = diff / 60;
        if (minutes < 60) {
            if (minutes < 6) {
                return "刚刚";
            }
            return minutes + "分钟前";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar calendarOld = Calendar.getInstance();
        Calendar calendarNew = Calendar.getInstance();
        calendarOld.setTime(createTime);
        calendarNew.setTime(now);
        if (calendarOld.get(Calendar.DAY_OF_YEAR) == calendarNew.get(Calendar.DAY_OF_YEAR)) {
            return "今天 " + sdf.format(createTime);
        }
        if (calendarOld.get(Calendar.DAY_OF_YEAR) == calendarNew.get(Calendar.DAY_OF_YEAR)-1){
            return "昨天 " + sdf.format(createTime);
        }

        if (calendarOld.get(Calendar.YEAR) == calendarNew.get(Calendar.YEAR)){
            sdf = new SimpleDateFormat("MM-dd HH:mm");
            return sdf.format(createTime);
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(createTime);
        }
    }

    @Test
    public void test17() {
        String str = getCbsTimeDiff(new Date());
        System.out.println(str);
    }

    @Test
    public void test18() {
        List<String> list = Arrays.asList("23","234");
        stringRedisTemplate.opsForHash().put("ss123",23+"",list);
    }

    @Test
    public void test19() {
        int n = 8 ;
        if (n == 8){
            System.out.println("88");
        } else if (n == 8){
            System.out.println("888");
        } else {
            System.out.println("8888");
        }
    }


    @Test
    public void test20() {
        InputStream inputStream = getImageFromURL("http://roi.skst.cn/goods/d1e/1451983024130_23772h.jpg");
        System.out.println(inputStream);
    }

    public static InputStream getImageFromURL(String urlPath) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            if (conn.getResponseCode() == 200) {
                return conn.getInputStream();
            } else{
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        return null;
    }

    @Test
    public void test21() {
        Random random = new Random();
        int n = random.nextInt(10);
        System.out.println(n);
    }

    @Test
    public void test22() throws IOException {
        Files.newBufferedReader(Paths.get(""), Charset.forName("GBK"));
    }

    @Test
    public void test23(){

        Long now = new Date().getTime();
        String nowStr = now.toString();
        System.out.println(nowStr);
        System.out.println(new Date());
        System.out.println(nowStr.substring(4));
        //
        System.out.println(1000*60*60*24*10);
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
    }

    @Test
    public void test24(){
        getCouponsOdds();
    }

    public void getCouponsOdds(){
        //查询该用户所有龙筹 分两种（1进入 赛事 查 普通/（足球或篮球）赛事 2进入押押，查 普通/押押）
        //CouponUserResponse(Long user_id, Long coupon_id, Integer price, Integer range_key, Date end_time)
        //默认 range_key = 0 联赛range_key = 3 赛事 range_key=2 押押 range_key=4
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
}
