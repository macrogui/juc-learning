package com.macro.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask<>(myThread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        System.out.println(futureTask.get());
    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        return 123;
    }
}
