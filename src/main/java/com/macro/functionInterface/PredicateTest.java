package com.macro.functionInterface;

import java.util.function.Predicate;

/**
 * 断定型输入接口，返回bool值
 */
public class PredicateTest {
    public static void main(String[] args) {
//        Predicate<Integer> predicate = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer i) {
//                return i == 0;
//            }
//        };
        Predicate<Integer> predicate = i -> i == 0;

        System.out.println(predicate.test(0));


    }
}
