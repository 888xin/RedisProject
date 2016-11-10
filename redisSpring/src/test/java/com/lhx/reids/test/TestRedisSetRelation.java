package com.lhx.reids.test;

import com.lhx.util.JdbcUtilByMysql;
import com.lhx.util.RedisUtil;
import com.lhx.util.RedisUtil2;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 * Created by lhx on 2016/9/2 14:52
 *
 * @Description
 */
public class TestRedisSetRelation {
    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis服务器
//        jedis = new Jedis("192.168.2.3", 6379);
        jedis = new Jedis("192.168.199.56", 19000);
        //权限认证
        //jedis.auth("admin");
    }


    @Test
    public void test() throws InterruptedException {

        Set<String> sets = jedis.keys("relationship-api:me_attention:*");
        System.out.println(sets.size());
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(sets);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        int total = queue.size();
        System.out.println(total);

        final SortedSet<Integer> setMax = new TreeSet<>();


            int index = 0;
            while (index < total){
                index ++ ;


                System.out.println(index+"=================");

                fixedThreadPool.execute(new Runnable() {
                    public void run() {



                        String key = null;
                        try {
                            key = queue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(key);
                        if (key != null){
                            Set<String> set2 = jedis.zrangeByScore(key, 1446307200000D, Double.MAX_VALUE);
                            if (set2.size() > 0){
                                String[] strs = set2.toArray(new String[0]);
                                setMax.add(strs.length);
                                //relationship-api:me_attention_v2:4
                                String key1 = key.substring(0, key.lastIndexOf(":"));
                                String key2 = key.substring(key.lastIndexOf(":")+1, key.length());
                                //redis换数据库
                                jedis.select(2);
                                jedis.sadd(key1+"_v2:"+key2, strs);
                            }
                        }



                    }
                });


            }




        System.out.println("数量最多达到："+ setMax.last());



    }

    public static void main(String[] args) throws SQLException {

//        transferData3("relationship-api:me_attention:","relationship-api:me_attention_v2:", 5000);
        transferData7("relationship-api:me_attention:","relationship-api:me_attention_v2:", 8000);
//        transferData4("relationship-api:me_attention:","relationship-api:me_attention_v2:", 3000);
//        transferData6("relationship-api:me_attention:","relationship-api:me_attention_v2:");
        //transferData("relationship-api:attention_me:*","relationship-api:attention_me_v2:", 100);
        //transferData("relationship-api:friend:*","relationship-api:friend_v2:", 100);

//        Jedis jedis = RedisUtil.getJedis();
//        Set<String> sets = jedis.keys("relationship-api:me_attention:1?");
//        Set<String> sets = jedis.zrangeByScore("relationship-api:me_attention:5", 1446307200000D, Double.MAX_VALUE);
//        System.out.println(sets.size());


    }

    private static void transferData(String regKey, final String newPreKey, int threadNum) {
        Jedis jedis = new Jedis("192.168.199.56", 19000);
        Set<String> sets = jedis.keys(regKey);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        final SortedSet<Integer> setMax = new TreeSet<>();
        //把全部任务放到fixedThreadPool中
        for (final String key : sets) {
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    Jedis jedis = RedisUtil.getJedis();
                    Set<String> set2 = jedis.zrangeByScore(key, 1446307200000D, Double.MAX_VALUE);
                    if (set2.size() > 0) {
                        String[] strs = set2.toArray(new String[0]);
                        setMax.add(strs.length);
                        //获取用户ID
                        String userId = key.substring(key.lastIndexOf(":") + 1, key.length());
                        //redis换数据库
                        jedis.select(2);
                        String newKey = newPreKey + userId;
                        jedis.sadd(newKey, strs);
                    }
                    RedisUtil.returnResource(jedis);
                }
            });
        }

        //shutdown会继续执行并且完成所有未执行的任务，shutdownNow 会清除所有未执行的任务并且在运行线程上调用interrupt() 。
        fixedThreadPool.shutdown();

        while (true){
            if (fixedThreadPool.isTerminated()){
                System.out.println("数量最多达到：" + setMax.last());
                break;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }


    private static void transferData2(String regKey, final String newPreKey, int threadNum) {
        Jedis jedis = new Jedis("192.168.2.3", 6379);
        Set<String> sets = jedis.keys(regKey);
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(sets);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        int total = queue.size();
        final SortedSet<Integer> setMax = new TreeSet<>();

        for (int i = 0; i < total; i++) {
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    String key = null;
                    try {
                        key = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(key);

                    if (key != null) {
                        Jedis jedis = RedisUtil.getJedis();
                        Set<String> set2 = jedis.zrangeByScore(key, 1446307200000D, Double.MAX_VALUE);
                        if (set2.size() > 0) {
                            String[] strs = set2.toArray(new String[0]);
                            setMax.add(strs.length);
                            //获取用户ID
                            String userId = key.substring(key.lastIndexOf(":") + 1, key.length());
                            //redis换数据库
                            jedis.select(2);
                            String newKey = newPreKey + userId;
                            jedis.sadd(newKey, strs);
                        }
                        RedisUtil.returnResource(jedis);
                    }

                }
            });

        }

        //shutdown会继续执行并且完成所有未执行的任务，shutdownNow 会清除所有未执行的任务并且在运行线程上调用interrupt() 。
        fixedThreadPool.shutdown();

        while (true){
            if (fixedThreadPool.isTerminated()){
                System.out.println("数量最多达到：" + setMax.last());
                break;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    private static void transferData3(final String regKey, final String newPreKey, int threadNum) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        final SortedSet<Integer> setMax = new TreeSet<>();
        //把全部任务放到fixedThreadPool中
        for (int i = 52762536; i > 1; i--) {
            final int userId = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(userId+"^^^^^^^^^^^");
                    //线上数据库
                    Jedis jedis2 = RedisUtil2.getJedis();
                    String oldKey = regKey+ userId ;
                    if (jedis2.exists(oldKey)){
                        Set<String> set2 = jedis2.zrangeByScore(oldKey, 1446307200000D, Double.MAX_VALUE);
                        RedisUtil2.returnResource(jedis2);
                        if (set2.size() > 0) {
                            String[] strs = set2.toArray(new String[0]);
                            setMax.add(strs.length);
                            //线下数据库
                            Jedis jedis = RedisUtil.getJedis();
                            //redis换数据库
                            jedis.select(2);
                            String newKey = newPreKey + userId;
                            jedis.sadd(newKey, strs);
                            RedisUtil.returnResource(jedis);
                        }
                    } else {
                        RedisUtil2.returnResource(jedis2);
                    }

                }
            });
        }


        //shutdown会继续执行并且完成所有未执行的任务，shutdownNow 会清除所有未执行的任务并且在运行线程上调用interrupt() 。
        fixedThreadPool.shutdown();

        while (true){
            if (fixedThreadPool.isTerminated()){
                System.out.println("数量最多达到：" + setMax.last());
                break;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    private static void transferData4(final String regKey, final String newPreKey, int threadNum) throws SQLException {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        JdbcUtilByMysql jdbcUtilByMysql = new JdbcUtilByMysql();
        jdbcUtilByMysql.getConnection();
        String sql = "select accountId from account where accountId > ? order by accountId LIMIT ";
        List<Object> params = new ArrayList<Object>();
        long accountId = 1 ;
        params.add(accountId);
        int limit = 100000 ;
        int size =  limit;
        while (size == limit){

            Set<String> userSets = new HashSet<>();

            List<Map<String,Object>> list = jdbcUtilByMysql.findMoreResult(sql + limit, params);
            //使用管道操作批量写入
            for (Map<String, Object> stringObjectMap : list) {
                String userSrt = stringObjectMap.get("accountId").toString();
                accountId = Long.valueOf(userSrt);
                userSets.add(userSrt);
            }
            transforData5(regKey, newPreKey, userSets, fixedThreadPool);
            //设置下一页
            size = list.size();
            params.clear();
            params.add(accountId);

        }



        //shutdown会继续执行并且完成所有未执行的任务，shutdownNow 会清除所有未执行的任务并且在运行线程上调用interrupt() 。
        fixedThreadPool.shutdown();






    }


    private static void transforData5(final String regKey, final String newPreKey, Set<String> userSets,ExecutorService fixedThreadPool) {
        //把全部任务放到fixedThreadPool中
        for (String userStr : userSets) {
            final String userId = userStr;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(userId+"^^^^^^^^^^^");
                    //线上数据库
                    Jedis jedis2 = RedisUtil2.getJedis();
                    String oldKey = regKey+ userId ;
                    if (jedis2.exists(oldKey)){
                        Set<String> set2 = jedis2.zrangeByScore(regKey+ userId, 1446307200000D, Double.MAX_VALUE);
                        RedisUtil2.returnResource(jedis2);
                        if (set2.size() > 0) {
                            String[] strs = set2.toArray(new String[0]);
                            //线下数据库
                            Jedis jedis = RedisUtil.getJedis();
                            //redis换数据库
                            jedis.select(2);
                            String newKey = newPreKey + userId;
                            jedis.sadd(newKey, strs);
                            RedisUtil.returnResource(jedis);
                        }
                    } else {
                        RedisUtil2.returnResource(jedis2);
                    }
                }
            });
        }






    }





    private static void transferData6(final String regKey, final String newPreKey) throws SQLException {

        JdbcUtilByMysql jdbcUtilByMysql = new JdbcUtilByMysql();
        jdbcUtilByMysql.getConnection();
        Jedis jedisOnline = RedisUtil2.getJedis();
        //线下数据库
        Jedis jedisOffline = RedisUtil.getJedis();
        //redis换数据库
        jedisOffline.select(2);

        String sql = "select accountId from account where accountId > ? order by accountId LIMIT ";
        List<Object> params = new ArrayList<Object>();
        long accountId = 1 ;
        params.add(accountId);
        int limit = 100000 ;
        int size =  limit;
        while (size == limit){

            Set<String> userSets = new HashSet<>();

            List<Map<String,Object>> list = jdbcUtilByMysql.findMoreResult(sql + limit, params);
            //使用管道操作批量写入
            for (Map<String, Object> stringObjectMap : list) {
                String userSrt = stringObjectMap.get("accountId").toString();
                accountId = Long.valueOf(userSrt);
                userSets.add(userSrt);
            }

            Pipeline p = jedisOffline.pipelined();

            boolean hasData = false;
            int n = 0;
            for (String userStr : userSets) {
                String oldKey = regKey+ userStr ;
                System.out.println(++n);
                if (jedisOnline.exists(oldKey)){
                    Set<String> set2 = jedisOnline.zrangeByScore(oldKey, 1446307200000D, Double.MAX_VALUE);
                    if (set2.size() > 0) {
                        String[] strs = set2.toArray(new String[0]);
                        hasData = true;
                        String newKey = newPreKey + userStr;
                        p.sadd(newKey, strs);
                    }
                }
            }
            if (hasData){
                p.sync();
            }


            //设置下一页
            size = list.size();
            params.clear();
            params.add(accountId);

        }




    }



    private static void transferData7(final String regKey, final String newPreKey, int threadNum) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        //把全部任务放到fixedThreadPool中
        for (int i = 0; i < 52770001;i += 6000) {
            final int userId = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {

                    long startTime = System.currentTimeMillis();

                    //线上数据库
                    Jedis jedis2 = RedisUtil2.getJedis();
                    Pipeline p2 = jedis2.pipelined();
                    Map<Integer, Response<Set<String>>> map = new HashMap<Integer, Response<Set<String>>>();
                    for (int j = 0; j < 6000; j++) {
                        String key = regKey + (userId + j);
                        Response<Set<String>> setResponse = p2.zrangeByScore(key, 1446307200000D, Double.MAX_VALUE);
                        map.put(userId + j, setResponse);
                    }
                    p2.sync();
                    RedisUtil2.returnResource(jedis2);

                    //线下数据库
                    Jedis jedis = RedisUtil.getJedis();
                    //redis换数据库
                    jedis.select(2);
                    Pipeline p1 = jedis.pipelined();

                    boolean hasData = false;
                    for (Integer userId : map.keySet()) {
                        Response<Set<String>> setResponse = map.get(userId);
                        Set<String> set = setResponse.get();
                        if (set.size() > 0){
                            hasData = true ;
                            String[] strs = set.toArray(new String[0]);
                            String newKey = newPreKey + userId;
                            p1.sadd(newKey, strs);


                        }

                    }
                    if (hasData){
                        p1.sync();
                    }
                    RedisUtil.returnResource(jedis);


                    System.out.println(userId+"^^^^^^^run time(s):^^^^"+(System.currentTimeMillis()-startTime)/1000);



                }
            });
        }


        //shutdown会继续执行并且完成所有未执行的任务，shutdownNow 会清除所有未执行的任务并且在运行线程上调用interrupt() 。
        fixedThreadPool.shutdown();


    }

    @Test
    public void test2() throws InterruptedException {

        String key = "relationship-api:me_attention:52702951";
        System.out.println(key);
        if (key != null){
            Set<String> set2 = jedis.zrangeByScore(key, 1446307200000D, Double.MAX_VALUE);
            if (set2.size() > 0){
                String[] strs = set2.toArray(new String[0]);
                String key1 = key.substring(0, key.lastIndexOf(":"));
                String key2 = key.substring(key.lastIndexOf(":")+1, key.length());
                String key3 = key1+"_v2:"+key2;
                jedis.select(2);
                jedis.sadd(key3, strs);
            }

        }



    }

    @Test
    public void test3() throws InterruptedException {

        final SortedSet<Integer> setMax = new TreeSet<>();
        setMax.add(4);
        setMax.add(467);
        setMax.add(34);
        setMax.add(1);
        setMax.add(45);
        setMax.add(2);
        setMax.add(4324);
        SortedSet<Integer> set = setMax.tailSet(10);
        SortedSet<Integer> set2 = setMax.subSet(setMax.size()-3, setMax.size());

        for (Integer integer : set2) {
            System.out.println(integer);
        }

        System.out.println(setMax.last());


    }


    @Test
    public void test4() throws InterruptedException {

        Set<String> sets = new HashSet<>();
        sets.add("23");
        sets.add("24");
        sets.add("25");
        sets.add("26");
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(sets);
        queue.take();
        System.out.println(queue.size());


    }


    @Test
    public void test5() throws InterruptedException {

        final Jedis jedis = new Jedis("192.168.2.3", 6379);
        jedis.select(2);
        Set<String> sets = jedis.keys("relationship-api:me_attention_v2:*");
        String[] strs = sets.toArray(new String[0]);
        Long result = jedis.del(strs);
        System.out.println(result);

    }

    @Test
    public void test6() throws InterruptedException {

        Set<String> sets = new HashSet<>();
        for (int i = 0; i < 100000000; i++) {
            sets.add("sdfsdfsdfewfewfdsfsdvcfgfdhfhgfjghmkuy"+i);
        }
        System.out.println(sets.size());

    }

    @Test
    public void test7() throws InterruptedException {

        Jedis jedisOnline = RedisUtil2.getJedis();
        Set<String> set = jedis.keys("relationship-api:me_attention_v2:10000*");
        System.out.println(set.size());

    }


    @Test
    public void test8() throws InterruptedException {

        final Jedis jedis = new Jedis("192.168.2.3", 6379);
        jedis.select(2);

        String result = jedis.flushDB();
        System.out.println(result);

    }

    @Test
    public void test9() throws InterruptedException {

        final Jedis jedis = new Jedis("192.168.2.3", 6379);
        jedis.select(1);
        String result = jedis.randomKey();
        System.out.println(result);

    }

    @Test
    public void test10()  {
        Jedis jedis = RedisUtil2.getJedis();
        Set<String> set =  jedis.zrangeByScore("relationship-api:me_attention:4", 1446307200000D, Double.MAX_VALUE);
        System.out.println(set.size());

    }




}
