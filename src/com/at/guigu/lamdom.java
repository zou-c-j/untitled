package com.at.guigu;

public class lamdom {
    public static void main(String[] args) {
      Test test =  (x,y) ->{
            System.out.println("啥也不输出");
            return x/y;
        };
        Test01 test01 = ()->{
            System.out.println("最简单的lamnda表达式");
        };
        System.out.println(test.div(10,2));
        test01.sayHello();
    }
}


interface Test {
    public int div(int x,int y);
    default int sayHello(){

        return 0;
    };
    default void sayHello01(){

    }
}

interface Test01{
    void sayHello();
}