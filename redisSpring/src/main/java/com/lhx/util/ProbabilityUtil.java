package com.lhx.util;

/**
 * Created by lhx on 15-9-11 下午2:28
 *
 * @Description
 */
public class ProbabilityUtil {

    /**
     * 掉出某个东西的概率
     * @param probabilities 概率字符串。比如a概率是30%，b的概率是70%，那就传"30,70"
     * @return 出现某个事物对应的位置，比如a概率是30%，b的概率是70%，传"30,70"，返回0，说明出现了“a”，返回1，出现“b"
     */
    public static short getCharacter(String probabilities) {
        Integer[] probabilitiesInt = strArray2intArray(probabilities);
        int random = (int) (Math.random() * 100 + 1);
        int sum = 0;
        for (short i = 0; i < probabilitiesInt.length; i++) {
            Integer n = probabilitiesInt[i];
            if (n != null) {
                if (n != 0) {
                    sum += n;
                    if (random <= sum) {
                        return i;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            return Short.MIN_VALUE;
        }
        return Short.MIN_VALUE;
    }

    //一堆整数（数之间用逗号隔开），转为整数数组
    private static Integer[] strArray2intArray(String arr){
        String[] strs = arr.split(",");
        Integer[] intArr = new Integer[strs.length];
        for (int i=0; i<strs.length; i++) {
            intArr[i] = Integer.parseInt(strs[i]);
        }
        return intArr;
    }
}
