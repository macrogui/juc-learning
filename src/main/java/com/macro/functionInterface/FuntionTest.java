package com.macro.functionInterface;

import java.util.function.Function;

/**
 * 函数型接口
 */
public class FuntionTest {
    public static void main(String[] args) {
//        Function<String, Integer> function = new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.valueOf(s);
//            }
//        };

        //lambda表达式
        Function<String, Integer> function = s -> Integer.valueOf(s);
        System.out.println(function.apply("123"));
    }
}
