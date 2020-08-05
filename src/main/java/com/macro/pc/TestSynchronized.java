package com.macro.pc;

public class TestSynchronized {
    public static void main(String[] args) {
        HotDogSynchronized hotDog = new HotDogSynchronized();
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    hotDog.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    hotDog.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    hotDog.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    hotDog.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}
