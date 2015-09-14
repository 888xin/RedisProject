package com.lhx.activity;

import com.lhx.service.MidautumnActivityService;
import com.lhx.util.ProbabilityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lhx on 15-9-11 下午2:14
 * 数组下标=0中；=1秋；=2国；=3庆；=4快；=5乐；=6自定义；=7（中秋快乐）；=8（国庆快乐）
 * @Description
 */
@Service
public class MidautumnActivity {

    @Autowired
    private MidautumnActivityService midautumnActivityService ;

    //接口3 获取接口 参数：用户accountId 用户拥有汉字记录数组
    public short[] get(long accountId){
        return midautumnActivityService.get(accountId);
    }

    /**
     * 接口4 获取+存储接口 （参数：用户accountId，概率因子字符串，类型）先根据用户accountId获取用户拥有的汉字记录数组，
     * 类型是抽奖的话，塞入自定义次数到数组中，存储类型是充值，送礼的话，需调用接口5，得到汉字，塞入到数组中，最后存储。
     * @param accountId
     * @param factor n=1 factor需要8个整数，逗号隔开的字符串；n=2 factor需要7个整数，逗号隔开的字符串；n=3 factor为6
     * @param type 类型 =0抽奖中任意字；=1送礼 =2充值（android）=3充值（IOS）
     * @return =0掉“中”；=1掉“秋”；=2掉“国”；=3掉“庆”；=4掉“快”；=5掉“乐”；=6掉自定义；=7掉“中国”；=负数 不掉字
     */
    public short insert(long accountId, String factor, int type){
        short[] result = midautumnActivityService.get(accountId);
        if (result == null){
            result = new short[9];
        }
        if (type == 0){
            result[6] ++ ;
            midautumnActivityService.put(accountId, result);
            return 6 ;
        } else if (type == 1){
            //调用概率接口
            short s = ProbabilityUtil.getCharacter(factor);
            if (s == 6){
                return Short.MIN_VALUE ;
            } else if (s == 7){
                result[0] ++ ;
                result = filterShorts(result, s);
                result[2] ++ ;
                result = filterShorts(result, s);
            }else {
                result[s] ++ ;
                //统计是否收集够字
                result = filterShorts(result, s);
            }
            midautumnActivityService.put(accountId, result);
            return s ;
        } else if (type == 2){
            short s = ProbabilityUtil.getCharacter(factor);
            if (s == 6){
                result[0] ++ ;
                result = filterShorts(result, s);
                result[2] ++ ;
                result = filterShorts(result, s);
            } else {
                result[s] ++ ;
                //统计是否收集够字
                result = filterShorts(result, s);
            }
            midautumnActivityService.put(accountId, result);
            return s ;
        }else if (type == 3){
            short s = ProbabilityUtil.getCharacter(factor);
            result[s] ++ ;
            //统计是否收集够字
            result = filterShorts(result, s);
            midautumnActivityService.put(accountId, result);
            return s ;
        }
        return Short.MIN_VALUE ;
    }

    //统计集够“中秋快乐”和“国庆快乐”的次数
    private short[] filterShorts(short[] s, int index) {
        switch (index) {
            case 0: {
                if (s[0] <= s[1] && s[0] <= s[4] && s[0] <= s[5]) {
                    //收集够“中秋快乐”
                    s[7]++;
                }
                return s;
            }
            case 1: {
                if (s[1] <= s[0] && s[1] <= s[4] && s[1] <= s[5]) {
                    //收集够“中秋快乐”
                    s[7]++;
                }
                return s;
            }
            case 2: {
                if (s[2] <= s[3] && s[2] <= s[4] && s[2] <= s[5]) {
                    //收集够“国庆快乐”
                    s[8]++;
                }
                return s;
            }
            case 3: {
                if (s[3] <= s[2] && s[3] <= s[4] && s[3] <= s[5]) {
                    //收集够“国庆快乐”
                    s[8]++;
                }
                return s;
            }
            case 4: {
                if (s[4] <= s[0] && s[4] <= s[1] && s[4] <= s[5]) {
                    //收集够“中秋快乐”
                    s[7]++;
                } else if (s[4] <= s[2] && s[4] <= s[3] && s[4] <= s[5]) {
                    //收集够“国庆快乐”
                    s[8]++;
                }
                return s;
            }
            case 5: {
                if (s[5] <= s[0] && s[5] <= s[1] && s[5] <= s[4]) {
                    //收集够“中秋快乐”
                    s[7]++;
                } else if (s[5] <= s[2] && s[5] <= s[3] && s[5] <= s[4]) {
                    //收集够“国庆快乐”
                    s[8]++;
                }
                return s;
            }
            default:
                return s;
        }
    }

    /**
     * 消费接口
     * @param accountId
     * @param type =1消费自定义字 =2消费“中秋快乐” =3消费“国庆快乐”
     * @param index =1消费自定义字时，这个参数需要传进来，表示用户想要哪个字
     * @return true 成功
     */
    public boolean consume(long accountId, int type, Integer index){
        short[] result = midautumnActivityService.get(accountId);
        if (result == null){
            return false ;
        }
        if (type == 1){
            if (result[6]<=0 || index == null){
                return false ;
            } else {
                result[6] -- ;
                result[index] ++ ;
                result = filterShorts(result, index);
                midautumnActivityService.put(accountId, result);
                return true ;
            }
        } else if (type == 2){
            if (result[7]<=0){
                return false ;
            } else {
                result[7] -- ;
                result[0] -- ;
                result[1] -- ;
                result[4] -- ;
                result[5] -- ;
                midautumnActivityService.put(accountId, result);
                return true ;
            }
        } else if (type == 3){
            if (result[8]<=0){
                return false ;
            } else {
                result[8] -- ;
                result[2] -- ;
                result[3] -- ;
                result[4] -- ;
                result[5] -- ;
                midautumnActivityService.put(accountId, result);
                return true ;
            }
        }
        return false ;
    }
}
