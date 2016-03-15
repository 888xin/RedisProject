package com.lhx.reids.test;

import java.util.LinkedList;

/**
 * Created by lhx on 15-11-27 下午5:47
 *
 * @Description
 */
public class TestLinkedList {

    public static void main(String[] args) {
        LinkedList<String> lList = new LinkedList<String>();
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");


        System.out.println("链表的第一个元素是 : " + lList.getFirst());
        System.out.println("链表最后一个元素是 : " + lList.getLast());

        System.out.println(lList.get(3));
    }
}
