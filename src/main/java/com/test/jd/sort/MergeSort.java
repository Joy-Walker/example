package com.test.jd.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int []arr = {9,8,7,6,0,4,3,2,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr){
        mergeSort(arr,0,arr.length - 1);
    }

    public static void mergeSort(int[] arr,int l ,int r){

        if(l >= r){
            return;
        }

        int mid = (l + r) / 2;
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int left = l;
        int right = mid + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        while(left <= mid && right <= r){
            help[i++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        while(left <= mid){
            help[i++] = arr[left++];
        }
        while ((right <= r)) {
            help[i++] = arr[right++];
        }
        for(i = 0; i < help.length ; i++){
            arr[i+l] = help[i];
        }
    }


}
