package com.at.day04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo{
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(0, 100000000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> joinTask = forkJoinPool.submit(myTask);
        System.out.println(joinTask.get());
        forkJoinPool.shutdown();

    }

}



class MyTask extends RecursiveTask<Integer> {
    private static final Integer ADJUST_VAULE = 10;
    private int start;
    private int end;
    private int result;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end-start<=ADJUST_VAULE){
            for (int i = start; i <=end ; i++) {
                result=result+i;
            }
        }else {

            int middle = (end + start)/2;
            MyTask myTask1 = new MyTask(start,middle);
            MyTask myTask2 = new MyTask(middle+1,end);
            myTask1.fork();
            myTask2.fork();
            result = myTask1.join()+myTask2.join();
        }

        return result;
    }
}
