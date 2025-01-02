package com.test.jd;

import java.util.Arrays;
import java.util.Random;

public class LotteryNumberGenerator {
    public static void main(String[] args) {
        System.out.println("本期大乐透号码可能是：");
        for(int i = 0; i < 5 ; i++) {
            int[] lotteryNumbers = generateLotteryNumbers();
            System.out.println(Arrays.toString(lotteryNumbers));
        }
    }

    // 生成大乐透号码的方法
    public static int[] generateLotteryNumbers() {
        int[] redBallNumbers = new int[5]; // 生成红球号码
        int[] blueBallNumbers = new int[2]; // 生成蓝球号码
        Random random = new Random();

        // 生成红球号码，范围为1~35，不重复
        for (int i = 0; i < redBallNumbers.length; i++) {
            int redBallNumber;
            do {
                redBallNumber = random.nextInt(35) + 1;
            } while (contains(redBallNumbers, redBallNumber));
            redBallNumbers[i] = redBallNumber;
        }

        // 生成蓝球号码，范围为1~12，不重复
        for (int i = 0; i < blueBallNumbers.length; i++) {
            int blueBallNumber;
            do {
                blueBallNumber = random.nextInt(12) + 1;
            } while (contains(blueBallNumbers, blueBallNumber));
            blueBallNumbers[i] = blueBallNumber;
        }

        // 对红球号码进行排序
        Arrays.sort(redBallNumbers);

        // 将红球号码和蓝球号码合并为一个号码数组
        int[] lotteryNumbers = new int[7];
        System.arraycopy(redBallNumbers, 0, lotteryNumbers, 0, redBallNumbers.length);
        System.arraycopy(blueBallNumbers, 0, lotteryNumbers, redBallNumbers.length, blueBallNumbers.length);

        return lotteryNumbers;
    }

    // 判断数组中是否包含指定的元素
    public static boolean contains(int[] array, int element) {
        for (int i : array) {
            if (i == element) {
                return true;
            }
        }
        return false;
    }
}
