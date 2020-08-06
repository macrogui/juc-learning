package com.macro.map;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap 并发异常 ConcurrentModificationException
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()-> {
                concurrentHashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,6));
                System.out.println(concurrentHashMap);
            }).start();
        }

    }
}
