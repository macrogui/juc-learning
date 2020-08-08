package com.macro.functionInterface;

import java.util.function.Supplier;

/**
 * 消费者接口，没有参数，有返回值
 */
public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "success";
        supplier.get();
    }
}
