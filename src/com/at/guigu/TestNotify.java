package com.at.guigu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestNotify{

    public static void main(String[] args) {

        Cake cake = new Cake();
        new Thread(()->{for (int i=0;i<10;i++) {
            try {
                cake.prodcue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        },"生产了").start();


        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    cake.sale();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费了").start();

        new Thread(()->{for (int i=0;i<10;i++) {
            try {
                cake.prodcue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        },"生产了").start();


        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    cake.sale();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费了").start();
    }



}

/*
//notify 不能与lock共用吗？？？
class Cake{

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void prodcue() throws InterruptedException {
        lock.lock();
        if (number!=0){
            this.wait();
        }
        try {
            System.out.println(Thread.currentThread().getName()+ ++number);
            this.notifyAll();
        } finally {
            lock.unlock();
        }
    }



    public void sale() throws InterruptedException {
        lock.lock();
        if (number==0){
            this.wait();
        }
        try {
            System.out.println(Thread.currentThread().getName()+ --number);
            this.notifyAll();
        } finally {
            lock.unlock();
        }
    }
}*/




class Cake{

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void prodcue() throws InterruptedException {
        lock.lock();
        while (number!=0){
           condition.await();
        }
        try {
            System.out.println(Thread.currentThread().getName()+ ++number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }



    public void sale() throws InterruptedException {
        lock.lock();
        while (number==0){
            condition.await();
        }
        try {
            System.out.println(Thread.currentThread().getName()+ --number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
