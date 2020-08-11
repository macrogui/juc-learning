package com.macro.volatileTest;

/**
 * volatile不保证原子性（要么都成功要么都失败）
 */
public class ACIDTest {
    private volatile static int num = 0;

    private static void add() {
        num++;
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
