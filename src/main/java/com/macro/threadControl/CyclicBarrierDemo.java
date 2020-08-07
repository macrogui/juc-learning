package com.macro.threadControl;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

/**
 * 与CountDownLatch相反
 * await来计数
 * 达到指定数量执行CyclicBarrier中代码
 * 否则会一直等待
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("达成5条，测试成功");
        });
        for (int i = 0; i < 8; i++) {
            int p = i;
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(p);
                    cyclicBarrier.await();
                }
            }).start();
        }
    }
}
