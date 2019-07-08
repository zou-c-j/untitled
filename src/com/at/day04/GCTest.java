package com.at.day04;

public class GCTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory()/(double)1024/1024+"MB");
        System.out.println(Runtime.getRuntime().totalMemory()/(double)1024/1024+"MB");
    }

}
