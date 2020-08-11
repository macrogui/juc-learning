package com.macro.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/** newInstance:
 *     if ((clazz.getModifiers() & Modifier.ENUM) != 0)
 *         throw new IllegalArgumentException("Cannot reflectively create enum objects");
 */
public enum EnumSingle{
    INSTANCE;
    public EnumSingle EnumSingle(){
        return INSTANCE;
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle enumSingle1 = EnumSingle.INSTANCE;
        //获取所有构造器
        for (Constructor<?> constructor : EnumSingle.class.getDeclaredConstructors()) {
            System.out.println(constructor);
        }
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle enumSingle2 = declaredConstructor.newInstance();
        //Exception in thread "main" java.lang.NoSuchMethodException
        System.out.println(enumSingle1);
        System.out.println(enumSingle2);
    }
}