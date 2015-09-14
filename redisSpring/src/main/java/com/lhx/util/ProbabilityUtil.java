package com.lhx.util;

/**
 * Created by lhx on 15-9-11 下午2:28
 *
 * @Description
 */
public class ProbabilityUtil {

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

    private static Integer[] strArray2intArray(String arr){
        String[] strs = arr.split(",");
        Integer[] intArr = new Integer[strs.length];
        for (int i=0; i<strs.length; i++) {
            intArr[i] = Integer.parseInt(strs[i]);
        }
        return intArr;
    }
}
