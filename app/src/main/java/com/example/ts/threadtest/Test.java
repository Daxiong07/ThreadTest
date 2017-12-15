package com.example.ts.threadtest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ts on 17-12-14.
 */

public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 15; i++) {
            ThreadPoolTest myTest = new ThreadPoolTest(i);
            executor.execute(myTest);
            System.out.println("线程池中线程数目： " + executor.getPoolSize() + ", 队列中等待执行的任务数目：　"
                    + executor.getQueue().size() + ", 已执行完别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
