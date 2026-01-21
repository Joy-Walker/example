package com.test;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author :panligang
 * @description :
 * @create :2023-12-14 16:21:00
 */

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        // Step 1️⃣: 定义两个差 1 的 long 值
        long originalA = 66072478512345670L;
        long originalB = 66072478512345671L;

        // Step 2️⃣: 把它们转成科学计数法字符串（模拟 ES 序列化）
        String sciA = String.format("%.16E", (double) originalA);
        String sciB = String.format("%.16E", (double) originalB);

        System.out.println("科学计数法格式:");
        System.out.println("a = " + sciA);
        System.out.println("b = " + sciB);

        // Step 3️⃣: 构造 JSON
        String json = String.format("{\"a\": %s, \"b\": %s}", sciA, sciB);
        System.out.println("\n构造的 JSON:");
        System.out.println(json);

        // Step 4️⃣: 用 Jackson 解析
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        // Step 5️⃣: 两种方式取值
        long a1 = root.get("a").asLong();
        long b1 = root.get("b").asLong();

        long a2 = new BigDecimal(root.get("a").asText()).longValue();
        long b2 = new BigDecimal(root.get("b").asText()).longValue();

        // Step 6️⃣: 对比输出
        System.out.println("\n原始 long:");
        System.out.println("a = " + originalA);
        System.out.println("b = " + originalB);
        System.out.println("a == b ? " + (originalA == originalB));

        System.out.println("\n直接 asLong() 结果:");
        System.out.println("a = " + a1);
        System.out.println("b = " + b1);
        System.out.println("a == b ? " + (a1 == b1));

        System.out.println("\nasText + BigDecimal 结果:");
        System.out.println("a = " + a2);
        System.out.println("b = " + b2);
        System.out.println("a == b ? " + (a2 == b2));
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // -4 -1 -1 0 1 2
            int left = i + 1;
            int right = nums.length - 1;
            int diff = 0 - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == diff) {
                    ret.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right) {
                        if (nums[left] == nums[left + 1]) {
                            left++;
                        } else {
                            break;
                        }
                    }
                    while (left < right) {
                        if (nums[right] == nums[right - 1]) {
                            right--;
                        } else {
                            break;
                        }
                    }
                    left++;
                    right--;
                } else if (nums[left] + nums[right] > diff) {
                    right--;
                } else {
                    left++;

                }
            }
        }
        return ret;
    }
}

