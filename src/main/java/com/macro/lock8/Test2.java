package com.macro.lock8;

import java.util.concurrent.TimeUnit;

/**
 * synchronized锁的是类
 * 两个类对象的类只有一个，所以只存在一把锁
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        StaticPhone phone1 = new StaticPhone();
        StaticPhone phone2 = new StaticPhone();
        new Thread(()->{
            phone1.sendSms();
        }, "A").start();

        TimeUnit.NANOSECONDS.sleep(1);

        new Thread(()->{
            phone2.call();
        }, "B").start();

        new Thread(()->{
            phone2.commonMethod();
        }, "C").start();
    }
}

class StaticPhone{
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public static synchronized void call(){
        System.out.println("call");
    }

    //非静态方法
    public synchronized void commonMethod(){
        System.out.println("common method");
    }

}