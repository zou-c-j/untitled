package com.at.guigu;


import java.util.concurrent.TimeUnit;

/**
 *
 * 题目：多线程8锁
 *   1 标准访问，请问先打印邮件还是短信？(邮件)
 *   2 邮件方法暂停4秒钟，请问先打印邮件还是短信？(邮件)
 *   3 新增一个普通方法hello()，请问先打印邮件还是hello？(hello)
 *   4 两部手机，请问先打印邮件还是短信？(短信)
 *   5 两个静态同步方法，同一部手机，请问先打印邮件还是短信？(邮件)
 *   6 两个静态同步方法，2部手机，请问先打印邮件还是短信？(邮件)
 *   7 1个普通同步方法,1个静态同步方法，1部手机，请问先打印邮件还是短信？(短信)
 *   8 1个普通同步方法,1个静态同步方法，2部手机，请问先打印邮件还是短信？(短信)
 * */
public class Lock8 {

    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{phone1.sendEmail();},"A").start();
//        new Thread(()->{phone.sayHello();},"C").start();
        new Thread(()->{phone2.sendMsg();},"B").start();

    }


}

class Phone{
    public static synchronized void sendEmail(){
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("sendEmail");
    }

    public  synchronized void sendMsg(){
        System.out.println("sendMsg");
    }

    public void sayHello(){
        System.out.println("hello");
    }

}