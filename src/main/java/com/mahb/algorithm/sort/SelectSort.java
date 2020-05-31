package com.mahb.algorithm.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-02 23:45
 **/
public class SelectSort {

    public static void main(String[] args) {
        int[] arr={10,2,101,29,68,92};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){

        for(int i=0; i<arr.length-1; i++){
            int minIndex =i;
            int min = arr[i];

            for(int j=i+1; j< arr.length; j++){
                if(min < arr[j]){
                    min = arr[j]; // 重置
                    minIndex = j; // 重置
                }
            }
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

    }
}
