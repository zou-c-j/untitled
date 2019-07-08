package com.at.guigu;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList线程不安全解决办法
 * 1.vector(),
 * 2.Collections.synchronizedList();集合工具类方法将集合转换成线程安全的
 * 3，CopyOnWriteArrayList写时复制，读写分离的变形
 */



public class notSafeDemmo {

    public static void main(String[] args) {
        List list = new ArrayList();
//        List list1 = Collections.synchronizedList(list);
        CopyOnWriteArrayList list1 = new CopyOnWriteArrayList(list);
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().replace("-","").substring(0,3));
                System.out.println(list1);
            },"AA").start();
        }
    }



}

