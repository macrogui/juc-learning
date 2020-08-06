package com.macro.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ConcurrentModificationException:并发修改异常
 * 解决方案：
 * 1. List<String> vectorList = new Vector<>();
 * 2. List<String> safeList = Collections.synchronizedList(new ArrayList<>());
 * 3. List<String> safeJucList = new CopyOnWriteArrayList<>();
 *
 * COW：写入时复制
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        List<String> vectorList = new Vector<>();
        List<String> safeList = Collections.synchronizedList(new ArrayList<>());
        List<String> safeJucList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                safeList.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(safeList);
            }).start();
        }
    }
}
