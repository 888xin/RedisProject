package com.lhx.reids.test;

import java.util.*;

import com.google.common.base.Joiner;
import com.lhx.util.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Created by lhx on 2016/9/2 14:52
 *
 * @Description
 */
public class TestRedisByJedis {
    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("localhost", 6379);
        //权限认证
        //jedis.auth("admin");
    }

    /**
     * redis存储字符串
     */
    @Test
    public void testString() {
        //-----添加数据----------
        jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin

        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name"));

        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","liuling","age","23","qq","476777XXX");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));

    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
    }

    /**
     * jedis操作List
     */
    @Test
    public void testList(){
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));
        //先向key java framework中存放三条数据
        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.del("java framework");
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","struts");
        jedis.rpush("java framework","hibernate");
        System.out.println(jedis.lrange("java framework",0,-1));
    }

    /**
     * jedis操作Set
     */
    @Test
    public void testSet(){
        //添加
        jedis.sadd("user","liuling");
        jedis.sadd("user","xinxin");
        jedis.sadd("user","ling");
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");
        //移除noname
        jedis.srem("user","who");
        System.out.println(jedis.smembers("user"));//获取所有加入的value
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user"));
        System.out.println(jedis.scard("user"));//返回集合的元素个数
        System.out.println(jedis.scard("host0"));//返回集合的元素个数
    }

    @Test
    public void test() throws InterruptedException {
        //jedis 排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a","6");
        jedis.lpush("a","3");
        jedis.lpush("a","9");
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果
        System.out.println(jedis.lrange("a",0,-1));
    }

    @Test
    public void testRedisPool() {
        RedisUtil.getJedis().set("newname", "中文测试");
        System.out.println(RedisUtil.getJedis().get("newname"));
    }

    @Test
    public void tesetZsetSinter() {
        jedis.zadd("test_xin", 3d, "apple");
        jedis.zadd("test_xin", 5d, "apple2");
        jedis.zadd("test_xin", 8d, "apple3");
        jedis.zadd("test_xin", 23d, "apple4");
        jedis.zadd("test_xin", 1d, "apple5");

        jedis.zadd("test_xin0", 3d, "apple4");
        jedis.zadd("test_xin0", 5d, "apple");
        jedis.zadd("test_xin0", 8d, "apple5");
        jedis.zadd("test_xin0", 23d, "apple6");
        jedis.zadd("test_xin0", 1d, "apple7");

        jedis.sadd("test_xin2", "apple3");
        jedis.sadd("test_xin2", "apple4");
        jedis.sadd("test_xin2", "apple5");

        jedis.sadd("test_xin3", "apple3");
        jedis.sadd("test_xin3", "apple4");
        jedis.sadd("test_xin3", "apple7");


        Set<String> seta = new HashSet<>();
        seta.add("apple");
        seta.add("lis");
        seta.add("xin");
        Set<String> set = jedis.sinter("test_xin3", "test_xin2");
        for (String s : set) {
            System.out.println(s);
        }

        //交集，score相加
        long result = jedis.zinterstore("test_xin_inter","test_xin", "test_xin0");
        System.out.println(result);
    }

    @Test
    public void testDelete() {
        Long result = jedis.del("host", "sub");
        System.out.println(result);
    }

    @Test
    public void testGetZSet() {
        Set<String> set = jedis.zrange("test_xin_inter", 0, 5);
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println(jedis.zscore("test_xin_inter", "apple"));
        System.out.println(jedis.zscore("test_xin_inter", "apple5"));
        System.out.println(jedis.zscore("test_xin_inter", "apple4"));

    }

    @Test
    public void testGetZSet2() {

        Set<String> set2 = new HashSet<>();
        set2.add("23");
        set2.add("24");
        set2.add("25");
        set2.add("26");
        String[] str = set2.toArray(new String[0]);
        jedis.sadd("test_xin_set", str);
    }

    @Test
    public void testGetZSet3() {
        Set<String> set = jedis.smembers("test_xin_set");
        for (String s : set) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetZSet4() {
        Set<String> set2 = jedis.zrangeByScore("test_xin_inter", 2D, Double.MAX_VALUE);
        String[] str = set2.toArray(new String[0]);
        jedis.sadd("test_xin_set", str);
    }

    @Test
    public void testGetZSet5() {
        Set<String> set2 = jedis.zrangeByScore("test_xin_inter", 2D, Double.MAX_VALUE);
        jedis.sunionstore("test_xin_set2", "sdf");
    }

    @Test
    public void testGetZSet6() {
        String str = "relationship-api:me_attention_v2:4";
        String str1 = str.substring(0, str.lastIndexOf(":"));
        String str2 = str.substring(str.lastIndexOf(":")+1, str.length());
        System.out.println(str1);
        System.out.println(str2);
    }


}
