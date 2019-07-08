package com.at.day03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {

    public static void main(String[] args) {
        String str = "1";
        Semaphore semaphore = new Semaphore(3);//模拟资源类，有三个车位
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(Thread.currentThread().getName() + "---离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    System.out.println(Thread.currentThread().getName() + "---离开了车位");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
