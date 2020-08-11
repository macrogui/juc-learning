package com.macro.singleton;

/**
 * 饿汉式，加载到内存时就会创建对象，可能会造成资源浪费
 */
public class HungryTest {
    private HungryTest(){

    }

    private final static HungryTest HUNGRY_TEST = new HungryTest();

    public static HungryTest getInstance(){
        return HUNGRY_TEST;
    }
}
