package com.macro.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式，创建对象时就会开辟空间，可能会造成资源浪费
 */
public class LazyManTest {
    private LazyManTest(){
        System.out.println(Thread.currentThread().getName());
    }

    /**
     * 必须要加volatile关键词避免由于指令重排导致的在多线程环境中获取到一个未初始化的对象（1->3->2）
     * 1. 分配内存空间
     * 2. 执行构造方法， 初始化对象
     * 3. 把对象指向这个空间
     */
    private volatile static LazyManTest LAZY_MAN_TEST = null;

    /**
     * 双重检测锁实现的懒汉式单例 DCL懒汉式
     * @return
     */
    public static LazyManTest getInstance(){
        if (LAZY_MAN_TEST == null){
            synchronized (LazyManTest.class){
                if (LAZY_MAN_TEST == null) {
                    LAZY_MAN_TEST = new LazyManTest();
                }
            }
        }
        return LAZY_MAN_TEST;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<LazyManTest> declaredConstructor = LazyManTest.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyManTest instance1 = declaredConstructor.newInstance();
        LazyManTest instance2 = getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
