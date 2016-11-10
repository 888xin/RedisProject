package com.lhx.reids.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lhx on 2016/10/10 9:33
 *
 * @Description
 */
public class TestSet {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>() {
            {
                add(1);
                add(3);
                add(5);
            }
        };

        Set<Integer> set2 = new HashSet<Integer>() {
            {
                add(1);
                add(2);
                add(3);
            }
        };

        result.clear();
        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("交集：" + result);

        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("差集：" + result);

        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("并集：" + result);


    }

}
