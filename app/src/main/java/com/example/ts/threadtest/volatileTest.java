package com.example.ts.threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ts on 17-12-14.
 */
public class volatileTest {
    public volatile int inc = 0;
    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        inc++;
        lock.unlock();
    }

    public static void main(String[] args) {
        final volatileTest test = new volatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                    System.out.println(Thread.currentThread().getName());
                }
            }.start();
        }

        while (Thread.activeCount() > 2) {
            System.out.println(Thread.currentThread().getName());
            //Thread.yield();
        }
        System.out.println(test.inc);
    }
}
