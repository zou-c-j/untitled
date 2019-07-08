package com.at.guigu;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList1 {
    public static void main(String[] args) {

        List list = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            list.add("AA" + i);
        }
        for (int i = 1; i <= 5; i++) {
            list.add("CC" + i);
        }
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

}
