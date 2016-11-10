package com.lhx.reids.test;

import com.lhx.util.Achievement;
import com.lhx.util.JdbcUtilByMysql;
import com.lhx.util.JedisUtil;
import com.lhx.util.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lhx on 2016/10/27 10:37
 *
 * @Description
 */
public class GetFriends {

    private Jedis jedis;

    private static final String HOST = "host";
    private static final String SUB = "sub";
    private static final String MAIN = "main";
    private static final String FRIEND = "friend:";

    //8月份
    private static final String MONTH_8 = "select msgId,senderId,receiverId from tb_commonmessage_log_201608 where msgId > ? order by msgId LIMIT ";
    //9月份
    private static final String MONTH_9 = "select msgId,senderId,receiverId from tb_commonmessage_log_201609 where msgId > ? order by msgId LIMIT ";
    //10月份
    private static final String MONTH_10 = "select msgId,senderId,receiverId from tb_commonmessage_log_201610 where msgId > ? order by msgId LIMIT ";



//    @Before
//    public void setup() {
//        //连接redis服务器，192.168.0.100:6379
//        jedis = new Jedis("localhost", 6379);
//        //权限认证
//        //jedis.auth("admin");
//    }

    @Before
    public void setup() {
        jedis = new Jedis("192.168.68.7", 6579);
        //权限认证
        jedis.auth("Lifeix2015");
        //jedis.select(0);
    }

    /**
     * 获取最近三个月的数据
     * @throws SQLException
     */
    @Test
    public void test() throws SQLException {

        getData(MONTH_8);
        getData(MONTH_9);
        getData(MONTH_10);


    }
    /**
     * 第一步，数据的初步处理
     * A对B聊天 A -> B  存储到主set中  B -> A 存储到副set中 如果B对A也聊过天，那么A -> B也存储到副set中
     * 主set与副set求交集，可求出互相聊过天的 A <- -> B 两个人
     */
    private void getData(String sql) throws SQLException {
        JdbcUtilByMysql jdbcUtilByMysql = new JdbcUtilByMysql();
        jdbcUtilByMysql.getConnection();
        List<Object> params = new ArrayList<Object>();
        long msgId = 0 ;
        params.add(msgId);
        int limit = 10000 ;
        int size =  limit;
        int index = 0 ;
        while (size == limit){

            System.out.println(msgId);
            index ++ ;
            System.out.println(index * limit);
            System.out.println("===============================");

            //List<Map<String,Object>> list = jdbcUtilByMysql.findMoreResult("select msgId,senderId,receiverId from tb_commonmessage_log_201609 group by senderId,receiverId HAVING msgId > ? order by msgId LIMIT " + limit, params);
            List<Map<String,Object>> list = jdbcUtilByMysql.findMoreResult(sql + limit, params);
            //使用管道操作批量写入
            Pipeline p = jedis.pipelined();
            for (Map<String, Object> stringObjectMap : list) {
                //添加
                Object hostObj = stringObjectMap.get("senderId");
                Object subObj = stringObjectMap.get("receiverId");

                p.sadd(HOST, hostObj + ":" + subObj);
                p.sadd(SUB, subObj + ":" + hostObj);

                msgId = Long.valueOf(stringObjectMap.get("msgId").toString());

            }
            p.sync();
            //设置下一页
            size = list.size();
            params.clear();
            params.add(msgId);

        }
    }

    /**
     * 第二步，取交集，每对数据表示里面的两个用户都互相聊过天
     */
    @Test
    public void testSinter() throws SQLException {
        //处理完后用redis求交集，结果存储到MAIN中
        //会导致超时，用连接池处理
        //jedis.sinterstore(MAIN, HOST, SUB);

        jedis = RedisUtil.getJedis();
        jedis.sinterstore(MAIN, HOST, SUB);
    }

    /**
     * 最后一步，获得某用户下所有与之有相互聊过天的记录
     */
    @Test
    public void getFriend() throws SQLException {
        //处理完后用redis求交集，结果存储到MAIN中
        Set<String> set = jedis.smembers(MAIN);
        for (String s : set) {
            String[] result = s.split(":");
            String key1 = result[0];
            String key2 = result[1];
            jedis.sadd(FRIEND + key1, key2);
            jedis.sadd(FRIEND + key2, key1);
        }
    }

    @Test
    public void test2() throws SQLException {
        Long result1 = jedis.scard(MAIN);
        Set<String> result2 = jedis.keys("friends:*");
        System.out.println(result1);
        System.out.println(result2.size());
    }

    @Test
    public void testDelete() throws SQLException {
        Long result = jedis.del(MAIN, HOST, SUB);
        System.out.println(result);
    }
}
