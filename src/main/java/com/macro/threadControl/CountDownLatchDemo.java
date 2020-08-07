package com.macro.threadControl;

import java.util.concurrent.CountDownLatch;

/**
 * 保证所有线程走完后，通过await，在执行后续操作
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" finished");
                countDownLatch.countDown();
            }).start();
        }

        //等待计数器归零
        countDownLatch.await();
//        countDownLatch.countDown();
        System.out.println("after finish");
    }
}
