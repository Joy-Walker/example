package com.example;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-18 19:06:00
 */
public class RemoveDuplicates {

    /**
     * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
     * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null) {
            return 0;
        }
        if(nums.length <= 1) {
            return 1;
        }
        int s = 0;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[s]) {
                nums[++s] = nums[i];
            }
        }
        return s + 1;
    }
}
