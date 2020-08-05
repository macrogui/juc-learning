package com.macro.sequence;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }}, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }}, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }}, "C").start();
    }
}

class Data{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int flag = 1; //1A 2B 3C
    public void printA(){
        try {
            lock.lock();
            while (flag!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+" --> A");
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        try {
            lock.lock();
            while (flag!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+" --> B");
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        try {
            lock.lock();
            while (flag!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+" --> C");
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
