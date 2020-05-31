package com.mahb.algorithm.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 08:52
 **/
public class InsertSort {
    public static void main(String[] args) {
        //  int[] arr={10,2,101,29,68,92};
        int[] arr= new int[80000];
        for(int i=0; i<80000; i++){
            arr[i] = (int)(Math.random() *  8000000);
        }

        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){

        int insertVal = 0;
        int insertIndex  =0;
        for(int i=0; i<arr.length; i++){
            insertVal = arr[i];
            insertIndex = i-1;
            while(insertIndex>=0 && insertVal > arr[insertIndex]){
                arr[insertIndex +1] = arr[insertIndex];
                insertIndex --;
            }
            if(insertIndex+1 != i){
                arr[insertIndex+1] = insertVal;
            }
        }
    }
}
