package com.macro.unsafe;

import java.util.*;

/**
 * ConcurrentModificationException:并发修改异常
 * 解决方案：
 * 1. List<String> vectorList = new Vector<>();
 * 2. List<String> safeList = Collections.synchronizedList(new ArrayList<>());
 * 3. List<String> safeJucList = new CopyOnWriteArrayList<>();
 *
 * COW：写入时复制
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 500; i++) {
            new Thread(()-> {
                synchronizedSet.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(synchronizedSet);
            }, String.valueOf(i)).start();
        }
    }
}
