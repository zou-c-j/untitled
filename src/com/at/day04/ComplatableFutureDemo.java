package com.at.day04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ComplatableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("没有返回值"));
        System.out.println(runAsync.get());*/
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            int age=10/0;
            return "xxx";
        });
        System.out.println(supplyAsync.whenComplete((x, y) -> {
            System.out.println("成功输出" + x);
            System.out.println("失败输出" + y);
        }).exceptionally(f -> {
            System.out.println(f.getMessage());
            return "44";
        }).get());

//        System.out.println(supplyAsync.get());

    }

}
