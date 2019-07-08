package com.at.guigu;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTiket {
    public static void main(String[] args) {

        TiccketWidow t = new TiccketWidow();

        new Thread(() -> {
            for (int i = 0; i <= 40; i++) t.saleNumber();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) t.saleNumber();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 40; i++) t.saleNumber();
        }, "C").start();
    }
}

class TiccketWidow {

    Lock lock = new ReentrantLock();


    private int number = 30;

    public void saleNumber() {


        try {
            lock.lock();
            if (number > 0) {

                System.out.println(Thread.currentThread().getName() + "\t卖出第" + (number--) + "票，还剩" + number + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

