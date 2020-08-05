package com.macro.pc;

public class HotDogSynchronized {
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            //wait
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "-->" + num);
        //通知其他线程完毕
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            //wait
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "-->" + num);
        //通知其他线程完毕
        this.notifyAll();
    }
}
