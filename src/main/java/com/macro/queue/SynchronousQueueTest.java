package com.macro.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列中最多只能存在一个元素
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                blockingQueue.put("1");
                System.out.println("put " + 1);
                blockingQueue.put("2");
                System.out.println("put " + 2);
                blockingQueue.put("3");
                System.out.println("put " + 3);
                blockingQueue.put("4");
                System.out.println("put " + 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(blockingQueue.poll());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(blockingQueue.poll());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(blockingQueue.poll());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(blockingQueue.poll());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
