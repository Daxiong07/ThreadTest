package com.example.ts.threadtest;

/**
 * Created by ts on 17-12-14.
 */

public class ThreadPoolTest implements Runnable {
    private int taskNum;

    public ThreadPoolTest(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}

