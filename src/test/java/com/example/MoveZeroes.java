package com.example;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-18 19:12:00
 */
public class MoveZeroes {


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        int s = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[s++] = nums[i];
            }
        }
        for(int i = s; s < nums.length; s++) {
            nums[s] = 0;
        }
    }
}
