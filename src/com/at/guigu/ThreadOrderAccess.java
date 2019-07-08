package com.at.guigu;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 * <p>
 * 1    高聚低合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * 4    标志位
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        Rourse rourse = new Rourse();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    rourse.BB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    rourse.AA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    rourse.CC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C").start();

    }


}

class Rourse {

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();



    public void AA() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            while (number == 1) {
                for (int i = 0; i < 1; i++) {
                    System.out.println("AA  ");
                }
                number = 2;
                condition2.signal();

            }
        } finally {
            lock.unlock();
        }
    }

    public void BB() throws InterruptedException {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            while (number == 2) {
                    for (int i = 0; i < 2; i++) {
                        System.out.println("BB  ");
                    }
                    number = 3;
                    condition3.signal();

            }
        } finally {
            lock.unlock();
        }
    }

    public void CC() throws InterruptedException {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            while (number == 3) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("CC  ");
                }
                number = 1;
                condition1.signal();

            }
        } finally {
            lock.unlock();
        }
    }

}
