package com.macro.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不保证原子性（要么都成功要么都失败）
 * AtomicInteger保证了原子操作，原理是调用了native的cas方法
 */
public class ACIDAtomicTest {
    private volatile static AtomicInteger num = new AtomicInteger();

    private static void add() {
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        //至少有两条（main线程和gc线程）
        System.out.println(Thread.activeCount());
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("num: " + num);
    }
}
