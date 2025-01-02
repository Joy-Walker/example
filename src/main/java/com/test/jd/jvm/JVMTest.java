package com.test.jd.jvm;

public class JVMTest {
    public static void main(String[] args) {
        new Thread (){
            public void run(){
                B.test();
            }
        }.start();

        new Thread(){
            public void run(){
                A.test();
            }
        }.start();
    }

}

class A{
    static{
        int a=0;
        System.out.println(a);
        B.test();
    }
    static void test(){
        System.out.println("调用了A的test方法");
    }
}

class B{
    static{
        int b=0;
        System.out.println(b);
        A.test();
    }
    static void test(){
        System.out.println("调用了B的test方法");
    }
}