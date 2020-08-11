package com.macro.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 普通的cas在多线程的情况下，进程不清楚数据是否经过了更新操作，若另一个线程经过两次cas操作，将数据恢复到原来的，而另一个线程并不知道这个数据是否经过cas操作。
 * aba影响：一个小偷，把别人家的钱偷了之后又还了回来，还是原来的钱吗？已经触犯了法律
 * AtomicStampedReference -> 维护一个版本号 解决ABA问题
 * 坑：
 * 对于 Integer var = ? 在-128 至 127 之间的赋值，Integer 对象是在 IntegerCache.cache 产生
 * 会复用已有对象，这个区间内的 Integer 值可以直接使用==进行判断
 * 但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。
 */
public class CASABADemo {
    public static void main(String[] args) {


        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 2);

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("A1 -> " + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(10, 11, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1));
            System.out.println("A2 -> " + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(11, 10, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1));
            System.out.println("A3 -> " + atomicStampedReference.getStamp());
        }).start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b init stamp" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B1 -> " + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(10, 11, stamp, stamp+1));
            System.out.println("B2 -> " + atomicStampedReference.getStamp());
        }).start();
    }
}
