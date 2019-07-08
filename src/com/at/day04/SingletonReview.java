package com.at.day04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingletonReview {

    //递归实现n！
    public static void main(String[] args) {


        Testn testn = new Testn();
        System.out.println(testn.getn(4));
        System.out.println(testn.getN1(11));
    }
}

class Testn {

    //n!
    public int getn(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * getn(n - 1);
        }

    }

    //斐波那契数列1,1,2,3,5,8,13,21,34,55,89
    public int getN1(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;

        } else {
            return getN1(n - 1) + getN1(n - 2);
        }
    }
}
//线程安全的单例模式

class NewInstance {
    private NewInstance() {
    }

    public NewInstance getInstance(NewInstance instance) {
        Lock lock = new ReentrantLock();
        try {
            if (instance == null) {
                lock.lock();
                instance = new NewInstance();
                return instance;
            } else {
                return instance;
            }
        } finally {
            lock.unlock();
        }
    }
}


class Singleton {

    //懒汉式线程安全(首选)
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
       if (instance == null) {
           synchronized (Singleton.class) {
               if (instance == null) {
                   instance = new Singleton();
               }
           }
        }
               return instance;
    }
}

class Singleton0{

    //静态内部类(既实现了线程安全，又避免了同步带来的性能的影响)
    private Singleton0() {}
    private static class LazyHolder{
        private static final Singleton0 INSTANC = new Singleton0();
    }
    public Singleton0 getInstance(){
        return LazyHolder.INSTANC;
    }
}


class Singleton1{
    //饿汉式单例(天生线程安全，但是会在未使用时就创建对象，会造成资源浪费，一般不考虑使用)
    private static Singleton1 instance= new Singleton1();

    private Singleton1() {}

    public Singleton1 getInstance(){

        return instance;
    }
}
