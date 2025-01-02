package com.test.jd.java8;

import java.util.function.Function;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-25 14:25:00
 */
public class TestFunction {

    public static void main(String[] args) {
       Function<String,String> function1 = a->{
           System.out.println("function1");
           return a;
       };

        Function<String,String> function2 = a->{
            System.out.println("function2");
            return a;
        };

        //
        function1.apply("a");

        function1.andThen(function2).andThen(function1).apply("1");
        function1.compose(function2).apply("a");


        test2("a",a->a.matches("aaa"));

    }

    public  static <T,R> R test(T t , Function<T,R> function,Function<R,R> function2) {

        return function.andThen(function2).apply(t);
    }



    public  static <T,R> R test2(T t , Function<T,R> function) {

        return function.apply(t);
    }


}
