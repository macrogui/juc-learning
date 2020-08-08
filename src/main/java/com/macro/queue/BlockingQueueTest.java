package com.macro.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue<E> extends Queue<E>
 * Queue<E> extends Collection<E>
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {

        //队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //等待2s，如果还是没有位置，就弹出
        blockingQueue.offer("wait", 2, TimeUnit.SECONDS);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(blockingQueue.remove());
        }).start();
        //put会一直等待，直到队列有空余位置
        blockingQueue.put("p");
        System.out.println(blockingQueue.offer("o"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.add("d"));
        //与put相反会一直等待，直到队列中有元素
        System.out.println(blockingQueue.take());

    }
}
