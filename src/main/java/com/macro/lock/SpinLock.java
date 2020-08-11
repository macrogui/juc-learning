package com.macro.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁,利用cas实现
 */
public class SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    //加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)){

        }
        System.out.println(Thread.currentThread().getName()+" -> myLock");
    }
    //解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+" -> myLock");
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();
        new Thread(()->{
            try {
                lock.myLock();
                System.out.println("A 业务代码");
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "A").start();

        new Thread(()->{
            try {
                lock.myLock();
                System.out.println("B 业务代码");
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "B").start();
    }
}
