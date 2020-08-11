package com.macro.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键词保证了线程的可见性
 */
public class JMMVolatileTest {
    private volatile static int num =0;

    public static void main(String[] args) {
        new Thread(()->{
            while (num==0){

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println("num: "+num);
    }
}
