package com.macro.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HotDogJuc {
    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        try {
            lock.lock();
            while (num != 0) {
                //wait
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            //通知其他线程完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        try {
            lock.lock();
            while (num == 0) {
                //wait
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "-->" + num);
            //通知其他线程完毕
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
