package com.macro.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 测试线程执行的优先级,多执行几次就懂了
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone1.sendSms();
        }, "A").start();

        new Thread(()->{
            phone2.call();
        }, "A").start();

        TimeUnit.MILLISECONDS.sleep(1);
        new Thread(()->{
            phone1.call();
        }, "B").start();

        new Thread(()->{
            phone1.commonMethod();
        }, "C").start();
    }
}

class Phone{
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }

    public synchronized void call(){
        System.out.println("call");
    }

    //普通方法
    public void commonMethod(){
        System.out.println("common method");
    }
}