package com.macro.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i < 10; i++) {
            final int tmp = i;
            new Thread(()->myCache.put(tmp+"", tmp+""), String.valueOf(i)).start();
        }

        for (int i = 1; i < 10; i++) {
            final int tmp = i;
            new Thread(()->myCache.get(tmp+""), String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    //可以同时读，不可同时写
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " 写入了 " + key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + " 写入成功 " + key);
        lock.writeLock().unlock();
    }

    public void get(String key){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " 读取 " + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取成功 ");
        lock.readLock().unlock();
    }
}
