package com.example;
/**
 * @author :panligang
 * @description :
 * @create :2024-03-18 19:32:00
 */
public class MinSubArrayLen {


    public int minSubArrayLen(int target, int[] nums) {
        int ret = Integer.MAX_VALUE;
        int sum = 0;
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                ret = Math.min(ret, i - j + 1);
                sum -= nums[j++];
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;

    }
}
