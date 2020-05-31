package com.mahb.algorithm.sort;

// import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @program: algorithm
 * @description:  基数排序
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 17:28
 *  https://code.i-harness.com/zh-CN/q/e98fa9
 **/
public class RadixSort {

    public static void main(String[] args) {
        // int[] arr={53,3,542,748,14,214};
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int[] arr= new int[890000];
        for(int i = 0; i < 890000; i++){
            arr[i] = (int)(Math.random() *  800000000);
        }

        Long start = System.currentTimeMillis();
        System.out.println(" 排序前时间是：" + sdf.format(start));
        radixSort(arr);
        Long end = System.currentTimeMillis() ;
        System.out.println(" 排序前时间是：" + sdf.format((end)));

        System.out.println(arr.length);
    }

    public static void radixSort(int[] arr){
        // 针对每个元素的个位进行排序

        // 二维数组表示10个桶；每个桶是一个一维数组；
        // 防止溢出，每个一维数组，大小定位arr.length;
        int[][] bucket = new int[10][arr.length];

        int max = arr[0];
        for(int i=1; i<arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max + "").length(); //  将最大的数 转换成 字符串；

        // 记录每个桶 实际放了多少个数据，定义一个一维数组记录各个桶每次放入的数据 个数；
        int[] bucketElementCounts = new int[10];

        for(int i=0 , n=1 ; i< maxLength; i++, n *= 10){

            // 针对每个元素对应位进行排序处理，第一次是个位，第二次是十位，第三次是百位.....依次类推
            for(int j=0; j<arr.length; j++){
                int digitOfElement = arr[j] / n % 10; // 取出每个元素对应位 的值
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j] ; // 放入对应的桶中
                bucketElementCounts[digitOfElement] ++ ;
            }

            // 按照这个桶的顺序 (一维数组的下标依次 取出数据，放入原理的数组)
            int index = 0;
            // 遍历每一桶，并将桶中的数据，放入到 原数组；
            for(int k=0; k<bucketElementCounts.length; k++){
                // 如果桶中，有数据，我们才 放入到原数组；
                if(bucketElementCounts[k] != 0){
                    // 循环该桶，即将 第 k 个桶 (即第 k 个一维数组)  ，放入
                    for(int l=0; l < bucketElementCounts[k]; l++){
                        arr[index] = bucket[k][l] ;
                        index++;
                    }
                }
                // 第 i+1 轮处理后，需要将每个 bucketElementCounts [k] 清空；
                bucketElementCounts[k] = 0;
            }
        }
    }
}
