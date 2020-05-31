package com.mahb.algorithm.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 11:37
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5,2,19,-52,8,9,13,3,6,28,-105,4,7,0};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left ;
        int r = right ;
        int pivot = arr[(left+right)/2] ;
        int temp =0;
        while(l < r){
            // 从左边开始找，找到大于等于 pivot的值
            while (arr[l] < pivot){
                l += 1;
            }
            // 从右边开始找，找到小于等于 pivot的值
            while (arr[r] > pivot){
                r -= 1;
            }
            if(l >= r){  // 如果 l >= r , 说明 左边全部是<= pivot的值；右边全是大于 >= pivot的 值
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if(arr[l] == pivot){  // 相等， r-- 前移
                r -= 1;
            }
            if(arr[r] == pivot){  // 相等， l++ 前移
                l += 1;
            }
        }
        if(l==r){
            l+=1;
            r-=1;
        }
        if(left < r){
            quickSort(arr,left,r);
        }
        if(right > l){
            quickSort(arr,l,right);
        }
    }
}
