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
public class TestList {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);

        Set<Integer> set2 = new HashSet<Integer>() {
            {
                add(1);
                add(2);
                add(3);
            }
        };

        result.clear();
        result.addAll(list1);
        result.retainAll(set2);
        System.out.println("交集：" + result);

        result.clear();
        result.addAll(list1);
        result.removeAll(set2);
        System.out.println("差集：" + result);

        result.clear();
        result.addAll(list1);
        result.addAll(set2);
        System.out.println("并集：" + result);


    }

}
