package com.test.jd.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TopK {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(smallestK(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)));
    }


    public static int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->b-a);
        for(int i = 0; i < arr.length; i++){
            if(queue.size() < k ){
                queue.offer(arr[i]);
            }else{
                if(arr[i] < queue.peek()){
                    queue.offer(arr[i]);
                }
            }
        }
        int[] ret = new int[k];
        int i = 0;
        while(!queue.isEmpty()){
            ret[i++] = queue.poll();
        }
        return ret;
    }
}
