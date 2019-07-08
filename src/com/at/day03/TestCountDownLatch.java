package com.at.day03;

import java.util.concurrent.CountDownLatch;

 class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName()+"  离开教室");
                downLatch.countDown();
            },String.valueOf(i)).start();

        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName()+"关门走人");
    }



        /* public static void main(String[] args) throws InterruptedException
         {
             CountDownLatch countDownLatch = new CountDownLatch(6);

             for (int i = 1; i <=6; i++)
             {
                 new Thread(() -> {
                     System.out.println(Thread.currentThread().getName()+"\t离开教室");
                     countDownLatch.countDown();
                 },String.valueOf(i)).start();
             }
             countDownLatch.await();
             System.out.println(Thread.currentThread().getName()+"\t班长关门走人");

         }
*/
}



