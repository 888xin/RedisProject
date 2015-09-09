package com.lhx.service;

import com.lhx.domain.User;
import com.lhx.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lhx on 15-9-2 下午2:15
 *
 * @Description
 */
@Service
public class LoginAccountService {

    private static final String ACCOUNT_LOGIN_STATUS = "account_login_status" ;

    @Autowired
    RedisTemplate<String, Long> redisTemplate;

    public void put(long accountId) {
        redisTemplate.opsForHash().put(ACCOUNT_LOGIN_STATUS, accountId, accountId + ":" + new Date().getTime());
    }

    public void put5(long accountId) {
        redisTemplate.opsForHash().put(ACCOUNT_LOGIN_STATUS, accountId, new Date().getTime());
    }

    public void put2(long accountId, Date date) {
        redisTemplate.opsForHash().put(ACCOUNT_LOGIN_STATUS, accountId, accountId + ":" + date.getTime());
    }

    /**
     * 状态可以由用户设置：隐身/离线等，可以重构此方法
     * @param accountId 账号
     * @param status 该账户的状态
     */
    public void put3(long accountId, int status) {
        //设置用户离线 设置用户活跃的时间为946656000000，2000年1月1号0时0分0秒（http://tool.chinaz.com/Tools/unixtime.aspx）
        //这样设置，好让后面统一进行判断是否在线，在getSessionAccount方法中判断，如果大于某值，就是用户自己设置的离线状态。
        if (status == 0){
            redisTemplate.opsForHash().put(ACCOUNT_LOGIN_STATUS, accountId, accountId + ":" + 946656000000L);
        } else if (status == 1){
            this.put(accountId);
        }
    }


    public void put4(long accountId, int status) {
        //设置用户离线 设置用户活跃的时间为946656000000，2000年1月1号0时0分0秒（http://tool.chinaz.com/Tools/unixtime.aspx）
        //这样设置，好让后面统一进行判断是否在线，在getSessionAccount方法中判断，如果大于某值，就是用户自己设置的离线状态。
        if (status == 0){
            redisTemplate.opsForHash().put(ACCOUNT_LOGIN_STATUS, accountId, accountId + ":" + 946656000000L);
        } else if (status == 1){
            this.put(accountId);
        }
    }

    public void put(long accountId, long date) {
        redisTemplate.opsForHash().put(ACCOUNT_LOGIN_STATUS, accountId, accountId + ":" + date);
    }

    public void delete(long accountId) {
        redisTemplate.opsForHash().delete(ACCOUNT_LOGIN_STATUS, accountId);
    }

    public String get(long accountId) {
        return (String) redisTemplate.opsForHash().get(ACCOUNT_LOGIN_STATUS, accountId);
    }

    public String get(long accountId, int second) {
        String result = this.get(accountId);
        Date logoutTime ;
        if (result != null){
            String[] strs = result.split(":");
            long time = Long.valueOf(strs[1]);
            long interval = (new Date().getTime() - time) / 1000 ;
            if (interval > second){
                logoutTime = new Date(time - (second * 1000));
            } else {
                //在线状态，不用返回该信息
                return null ;
            }
        } else {
            //如果为null，随机分配一个不在在线的时间内的离线时间(15天内的某个时间)，并存入到redis中（防止刷新后时间不同）
            long random = (long) (Math.random()*(1000 * 60 * 60 * 24 * 15)) + (second * 1000);
            long time = new Date().getTime() - random ;
            logoutTime = new Date(time);
            this.put2(accountId,logoutTime);
        }
        return TimeUtil.getStandardDateTime(logoutTime) ;
    }

    /**
     * 用户离线时间获取
     * @param accountId 账号
     * @param second 离活跃时间多少秒属于不在线
     * @return 用户有活跃时间并且超过了second，就返回离线时间，否则返回null；
     *         用户没有活跃时间，15天内随机的时间作为活跃时间，存入到Redis里面，并且返回。
     */
    @Deprecated
    public String get3(long accountId, int second) {
        String result = this.get(accountId);
        Date logoutTime ;
        if (result != null){
            String[] strs = result.split(":");
            long time = Long.valueOf(strs[1]);
            long interval = (new Date().getTime() - time) / 1000 ;
            if (interval > second){
                logoutTime = new Date(time - (second * 1000));
            } else {
                //在线状态，不用返回该信息
                return null ;
            }
        } else {
            //如果为null，随机分配一个不在在线的时间内的离线时间(15天内的某个时间)，并存入到redis中（防止刷新后时间不同）
            long random = (long) (Math.random()*(1000 * 60 * 60 * 24 * 15)) + (second * 1000);
            long time = new Date().getTime() - random ;
            logoutTime = new Date(time);
            this.put(accountId, logoutTime.getTime());
        }
        return TimeUtil.getStandardDateTime(logoutTime) ;
    }

    public Collection getMulti(Collection<Object> ids) {
        return redisTemplate.opsForHash().multiGet(ACCOUNT_LOGIN_STATUS, ids);
    }

    public Map<Long, Boolean> getLoginMap(Collection<Object> ids, int second) {
        //获取到的collection绝对不为null，参数ids有多少个值，collection就有多少个值，redis里面没有的，为null
        Collection collection = this.getMulti(ids);
        Map<Long, Boolean> map = new HashMap<Long, Boolean>();
        if (collection.size() > 0){
            //对有值的元素进行判断，大于second既为离线状态，无值元素直接为离线状态
            for (Object object : collection) {
                if (object != null){
                    String[] strs = object.toString().split(":");
                    long accountId = Long.valueOf(strs[0]);
                    long time = Long.valueOf(strs[1]);
                    long interval = (new Date().getTime() - time) / 1000 ;
                    //大于这个时间，就是离线状态
                    if (interval > second){
                        map.put(accountId, false);
                    } else {
                        map.put(accountId, true);
                    }
                    //有值的accountId从集合中去除
                    ids.remove(accountId);
                }
            }
            //redis里面没有值的，都设为离线状态
            if (ids.size() > 0){
                for (Object id : ids) {
                    map.put((Long)id, false);
                }
            }
        }
        return map;
    }


    //有问题
    @Deprecated
    public Map<Long, Boolean> getLoginMap2(Collection<Object> ids, int second) {
        Collection collection = this.getMulti(ids);
        Map<Long, Boolean> map = new HashMap<Long, Boolean>();
        if (collection.size() != 0){
            //对有值的元素进行判断，大于second既为离线状态
            for (Object object : collection) {
                String[] strs = object.toString().split(":");
                long accountId = Long.valueOf(strs[0]);
                long time = Long.valueOf(strs[1]);
                long interval = (new Date().getTime() - time) / 1000 ;
                //大于这个时间，就是离线状态
                if (interval > second){
                    map.put(accountId, false);
                } else {
                    map.put(accountId, true);
                }
                //标识过了,从集合中去除
                ids.remove(accountId);
            }
            //redis里面没有值的，都设为离线状态
            if (ids.size() > 0){
                for (Object id : ids) {
                    map.put((Long)id, false);
                }
            }
        } else {
            for (Object id : ids) {
                map.put((Long)id, false);
            }
        }
        return map;
    }

    public Map<Long, Boolean> getLoginStatusMap3(Collection<Object> ids, int second) {
        //获取到的collection绝对不为null，参数ids有多少个值，collection就有多少个值，redis里面没有的，为null
        Collection collection = redisTemplate.opsForHash().multiGet(ACCOUNT_LOGIN_STATUS, ids);
        //集合没有get()方法，转换为数组来定位
        Object[] accountIds = ids.toArray();
        Map<Long, Boolean> map = new HashMap<Long, Boolean>();
        int no = 0 ;
        if (collection.size() > 0){
            //对有值的元素进行判断，大于second既为离线状态，无值元素直接为离线状态
            for (Object object : collection) {
                if (object != null){
                    long time = (Long) object;
                    long interval = (new Date().getTime() - time) / 1000 ;
                    //大于这个时间，就是离线状态
                    if (interval > second){
                        map.put((Long) accountIds[no], false);
                    } else {
                        map.put((Long) accountIds[no], true);
                    }
                } else {
                    map.put((Long) accountIds[no], false);
                }
                no ++ ;
            }
        }
        return map;
    }

    private String getTimeDifferenceByTimestamp(long currTime, long createTime) {
        int hours = (int) ((currTime - createTime) / (1000*60*60));
        int minutes = (int) ((currTime - createTime) / (1000*60));
        if (hours == 0){
            if (minutes == 0) {
                minutes = 1;
            }
            return minutes + "分钟前";
        } else if (hours >= (24*30)){
            return hours / (24*30) + "月前";
        } else if (hours >= 24){
            return hours / 24 + "天前";
        } else {
            return hours + "小时前";
        }
    }

    public String get2(long accountId, int second) {
        String result = this.get(accountId);
        if (result != null){
            String[] strs = result.split(":");
            long time = Long.valueOf(strs[1]);
            long currentTime = new Date().getTime();
            long interval = (currentTime - time) / 1000 ;
            if (interval > second){
                return getTimeDifferenceByTimestamp(currentTime,time);
            } else {
                //在线状态，不用返回该信息
                return null ;
            }
        } else {
            //如果为null，随机分配一个不在在线的时间内的离线时间(15天内的某个时间)，并存入到redis中（防止刷新后时间不同）
            long random = (long) (Math.random()*(1000 * 60 * 60 * 24 * 15)) + (second * 1000);
            long currentTime = new Date().getTime();
            long time = currentTime - random ;
            return getTimeDifferenceByTimestamp(currentTime,time);
        }
    }


}



