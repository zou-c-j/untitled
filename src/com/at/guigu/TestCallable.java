package com.at.guigu;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 多线程中，第3种获得多线程的方式
 *
 * 1 get方法一般请放在最后一行
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       /* FutureTask futureTask = new FutureTask(new MyThread());

        (new Thread(futureTask,"A")).start();
        (new Thread(futureTask,"B")).start();

        System.out.println(Thread.currentThread().getName()+"*****计算完成");
        System.out.println(Thread.currentThread().getName()+futureTask.get());*/

       new Thread(new FutureTask<>(new Callable<String>() {
           @Override
           public String call() throws Exception {
               System.out.println("这样写有意义吗？");
               return "呵呵";
           }
       })).start();
    }
}


class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("测试执行几次。。");
        return "哈哈";
    }
}




/**
 * 老师的代码
 *
 *太简单了，么得必要
 *
 *
 * */



























