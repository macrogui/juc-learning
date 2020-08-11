package com.macro.lock;

public class SynchronizeDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sms();
        }, "A").start();
        new Thread(()->{
            phone.sms();
        }, "B").start();
    }
}

class Phone{
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName()+" sms");
        call();
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+" call");
    }
}
