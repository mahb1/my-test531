package com.mahb.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 10:39
 **/
public class ShellSort {
    public static void main(String[] args) {

        //int[] arr = {5,2,19,8,9,13,3,6,28,4,7,0};
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int[] arr= new int[80001];
        for(int i=0; i<80001; i++){
            arr[i] = (int)(Math.random() *  8000000);
        }
        Long start = System.currentTimeMillis();
        System.out.println(" 排序前时间是：" + sdf.format(start));
        shellSortShift(arr);
        Long end = System.currentTimeMillis() ;
        System.out.println(" 排序前时间是：" + sdf.format((end)));
        // System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);

    }

    // 交换法
    public static void shellSortSwop(int[] arr){

        int temp = 0;
        for(int gap = arr.length / 2; gap>0; gap /=2){
            for(int i=5; i<arr.length; i++){
                for(int j=i-gap; j>=0; j-=gap){
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    // 移位法
    public static void shellSortShift(int[] arr){
        //  增量gap， 并逐步缩小增量
        for(int gap = arr.length / 2; gap>0; gap /=2){
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for(int i=gap; i<arr.length; i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j-gap]){
                    while(j - gap >=0 && temp < arr[ j - gap]){
                        arr[j] = arr[j - gap];
                        j -= gap ;
                    }
                    // 退出 while 后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }

        }
    }
}
