package com.macro.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如果满足期望值，就更新，否则不更新
 * java不可以直接操作内存 但可以通过c++ native， unsafe是java的后门，可以通过这个来操作内存
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        System.out.println(atomicInteger.compareAndSet(100, 200));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(100, 300));
        System.out.println(atomicInteger.get());
    }
}
