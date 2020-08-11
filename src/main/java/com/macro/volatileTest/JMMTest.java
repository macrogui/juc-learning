package com.macro.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * 程序死循环
 * 程序不知道num值在主内存已经修改过了
 * 线程对主内存变化不知道
 */
public class JMMTest {
    private static int num =0;

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
