package com.at.day03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        MyChache myChache = new MyChache();
        for (int i = 0; i < 5; i++) {
            int tempI = i;
            new Thread(() -> {
                myChache.put(tempI + "", tempI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int tempI = i;
            new Thread(() -> {
                myChache.get(tempI + "");
            }, String.valueOf(i)).start();
        }

    }

}


/**
 * @auther zzyy
 * @create 2019-02-20 14:08x
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 * 小总结：
 *          读-读能共存
 *          读-写不能共存
 *          写-写不能共存
 */


class MyChache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "存入值");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "存入成功");
        } finally {

        readWriteLock.writeLock().unlock();
        }
    }


    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取值");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取成功");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}