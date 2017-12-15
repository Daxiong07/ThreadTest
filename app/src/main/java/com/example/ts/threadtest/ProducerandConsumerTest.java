package com.example.ts.threadtest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ts on 17-12-14.
 */

public class ProducerandConsumerTest {

    public static void main(String[] args) {
        Queue<Integer> food = new LinkedList<>();
        int maxNumber = 1;

        Producer producer = new Producer(maxNumber, "Producer", food);
        Consumer consumer = new Consumer(maxNumber, "Consumer", food);
//        Consumer consumer1 = new Consumer(maxNumber, "Consumer1", food);
//        Consumer consumer2 = new Consumer(maxNumber, "Consumer2", food);
//        Consumer consumer3 = new Consumer(maxNumber, "Consumer3", food);

        new Thread(producer).start();
        new Thread(consumer).start();
//        new Thread(consumer1).start();
//        new Thread(consumer2).start();
//        new Thread(consumer3).start();

    }


}

class Producer implements Runnable {
    private int maxNumber;
    private String mName;
    private Queue<Integer> queue;

    public Producer(int maxNumber, String name, Queue<Integer> queue) {
        this.maxNumber = maxNumber;
        this.mName = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxNumber) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(1);
                System.out.println(mName + ", produce left:" + queue.size());
                queue.notifyAll();
//                try {
//                    //让出CPU资源，但是不会释放锁资源。
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}

class Consumer implements Runnable {
    private int maxNumber;
    private String mName;
    private Queue<Integer> queue;

    public Consumer(int maxNumber, String name, Queue<Integer> queue) {
        this.maxNumber = maxNumber;
        this.mName = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.remove();
                System.out.println(mName + ", produce left:" + queue.size());
                queue.notifyAll();
//                try {
//                    //让出CPU资源，但是不会释放锁资源。
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}

