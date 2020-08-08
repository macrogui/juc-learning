package com.macro.functionInterface;

import java.util.function.Consumer;

/**
 * 消费型接口，无返回值
 */
public class ConsumerTest {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        Consumer<String> consumer = System.out::println;
        consumer.accept("hello world");
    }
}
