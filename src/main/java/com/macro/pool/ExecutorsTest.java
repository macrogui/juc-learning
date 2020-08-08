package com.macro.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不建议通过Executors创建线程，建议通过ThreadPoolExecutor创建
 * 本质：ThreadPoolExecutor
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        //单个线程
//        ExecutorService executor = Executors.newSingleThreadExecutor();
        //固定线程大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //可伸缩的线程池
//        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 50; i++) {
                executor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 执行");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
