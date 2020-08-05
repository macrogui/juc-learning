package com.macro.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket2 {
    private int num = 600;

    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if (num>0) {
                System.out.println(Thread.currentThread().getName()+"卖出了第"+num--+"张票,剩余: "+num);
            }
        } finally {
            lock.unlock();
        }

    }
}