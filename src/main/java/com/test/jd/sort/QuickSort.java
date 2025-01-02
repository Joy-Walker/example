package com.test.jd.sort;

import java.util.Arrays;

public class QuickSort {


    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,-1,0,88,100};
        quickSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length - 1);
    }

    public static void quickSort(int[] arr,int l,int r){
        if(l >= r){
            return;
        }
        int[] ret = partition(arr, l, r);

        quickSort(arr,l,ret[0]);
        quickSort(arr,ret[1],r);

    }

    public static int[] partition(int[] arr,int l,int r){
        int less = l - 1;
        int more = r + 1;
        int val = arr[l];

        while(l < more){
            if(arr[l] < val){
                swap(arr,++less,l++);
            }else if(arr[l] > val){
                swap(arr,l,--more);
            }else{
                l++;
            }
        }
        return new int[]{less,more};

    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
