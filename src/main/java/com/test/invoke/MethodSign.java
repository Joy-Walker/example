package com.test.invoke;

/**
 * @author :panligang
 * @description :
 * @create :2024-12-25 19:19:00
 *
 * java虚拟机规范中，类名+方法名+参数类型+返回值类型唯一标识一个方法，因为返回值不一样，虚拟机能判别调用哪个方法
 * 但是Java语言中限制方法名相同方法参数相同返回值不同的方法出现在同一个类中
 *
 * Java 编译器会将所有对非私有实例方法的调用编译为需要动态绑定的类型，因为可能存在子类重写
 *
 * invokestatic:调用静态方法
 * invokevirtual:调用非私有实例方法
 * invokeinterface:调用接口方法
 * invokespecial:调用私有实例方法，包括构造器，super.xx的方法和所实现接口的默认方法
 * invokedynamic:调用动态方法
 *
 * 虚方法调用，invokevirtual、invokeinterface 运行期才可以决定调用某个方法，存在重写 动态绑定
 *
 * 基于虚方法表，在类加载的准备阶段生成。
 * 在真正调用方法的时候，虚拟机根据真实的对象类型，查找给对象类型的方法表进而完成真正的方法调用
 *
 *
 *
 */
public class MethodSign {

    public void test(){
        System.out.println(222);
    };
//
//    public int test(){return 1}

    interface Customer {
        boolean isVIP();
    }

    class Merchant {
        public Number actionPrice(double price, Customer customer) {
            return 1;
        }
    }

    class NaiveMerchant extends Merchant {
        @Override
        public Double actionPrice(double price, Customer customer) {
            return 1.0;
        }
    }
}
