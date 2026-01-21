package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-22 22:20:00
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.client")
public class ClientMain {
    public static void main(String[] args) {
        SpringApplication.run(ClientMain.class, args);
    }


    public static String createQueryParameters(String api, Map<String, String> conditions) {
        String params = conditions.entrySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining("&"));
        return api + "?" + params;
    }

    // [1,3,-1,-3,5,3,6,7]
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ret = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                ret[i - k + 1] = nums[deque.peek()];
            }
        }
        return ret;
    }

    // s = "ADOBECODEBANC", t = "ABC" BANC
    public String minWindow(String s, String t) {
        int[] tFeq = new int[128];
        for(char c : t.toCharArray()) {
            tFeq[c]++;
        }
        int[] curWindow = new int[128];
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int count = 0;
        int start = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if(tFeq[c] > 0) {
                curWindow[c]++;
                if(curWindow[c] <= tFeq[c]) {
                    count++;
                }
            }
            right++;
            while (count == t.length()) {
                if(right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char d = s.charAt(left);
                if(tFeq[d] > 0) {
                    curWindow[d]--;
                    if(curWindow[d] < tFeq[d]) {
                        count--;
                    }
                }
                left++;
            }

        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }


}
