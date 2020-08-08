package com.macro.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 执行计算任务
 * end - start < changeValue 时执行普通计算
 * 否则通过 ForkJoinTask 执行
 * 普通方法共计用时5721
 * forkJoin共计用时5203
 * Stream流式计算共计用时236 (效率最高)
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long changeValue;

    public ForkJoinTest(Long start, Long end, Long changeValue) {
        this.start = start;
        this.end = end;
        this.changeValue = changeValue;
    }


    private Long normal() {
        Long sum = 0L;
        for (Long i = start; i < end + 1; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(method01());
        System.out.println(method02());
        System.out.println(method03());
    }

    private static long method01(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 0L; i < 5_0000_0000 + 1; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("普通方法共计用时"+(end-start));
        return sum;
    }

    private static long method02() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinTest(0L, 5_0000_0000L, 10000000L);
        forkJoinPool.submit(forkJoinTask);
        Long sum = forkJoinTask.get();
        long end = System.currentTimeMillis();
        System.out.println("forkJoin共计用时"+(end-start));
        return sum;
    }

    private static long method03() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 5_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("Stream流式计算共计用时"+(end-start));
        return sum;
    }

    @Override
    protected Long compute() {
        if (end - start < changeValue) {
            return normal();
        } else {
            long mid = (start + end) / 2;
            ForkJoinTest t1 = new ForkJoinTest(start, mid, changeValue);
            t1.fork();
            ForkJoinTest t2 = new ForkJoinTest(mid + 1, end, changeValue);
            t2.fork();
            return t1.join()+t2.join();
        }
    }
}
