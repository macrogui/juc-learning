package com.macro.threadControl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()-> {
                try {
                    //acquire()获得
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到线程");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"释放线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //release()释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
