package com.test.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author :panligang
 * @description :
 * @create :2024-12-25 19:40:00
 * <p>
 * InvokeStatic
 * 该指令的调用机制抽象出调用点这一个概念，并允许应用程序将调用点链接至任意符合条件的方法上。
 * <p>
 * MethodHandler 方法句柄
 * MethodType 方法的参数类型和返回值类型组成，来确认方法句柄是否适配的唯一关键，不关心类和方法名称
 */
public class InvokeStaticExample {

    private int num = 1;

    public void test() {
        System.out.println(111);
    }

    public static void main(String[] args) throws Throwable {
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodType methodType = MethodType.methodType(int.class);
//        MethodHandle methodHandle = lookup.findGetter(InvokeStaticExample.class, "num", int.class);
//        methodHandle.invokeExact();
//        MethodHandle methodHandle1 = methodHandle.asType(methodType);
//        MethodHandle methodHandle2 = methodHandle1.bindTo(new InvokeStaticExample());

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(InvokeStaticExample.class, "test", MethodType.methodType(void.class));
        methodHandle.invoke(new InvokeStaticExample());



    }


}
