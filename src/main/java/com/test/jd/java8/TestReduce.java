package com.test.jd.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author :panligang
 * @description :
 * @create :2022-08-14 21:56:00
 *
 * 将流的元素组合起来
 */
public class TestReduce {

    public static void main(String[] args) {

        /**
         * 0 初始值
         * (a,b)->a+b 将两个元素结合产生一个新值
         */
        int sum = IntStream.range(0, 10).reduce(0, (a,b)->a+b);
        System.out.println(sum);

        int max = IntStream.range(0, 10).reduce(Math::max).orElse(0);

        List<Integer> data = Arrays.asList(1, 2, 3).stream().filter(o -> o > 1).collect(Collectors.toList());

        System.out.println(data);

        List<Integer> list = Arrays.asList(1, 2, 3);

        List<List<Integer>> lists = Arrays.asList(list);





    }

}
