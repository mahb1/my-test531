package com.mahb.algorithm.sort;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 15:49
 **/
public class MergeSort {

    public static void main(String[] args) {
        int arr[] = {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];
        mergersort(arr,0,arr.length-1, temp);

        System.out.println(" 排序后："+ Arrays.toString(arr));

    }

    public static void mergersort(int arr[], int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) /2;
            mergersort(arr, left,mid,temp);
            mergersort(arr, mid+1,right,temp);

            merger(arr,left,mid,right,temp);
        }
    }

    /**
    * @Description:
    * @Param: [arr, left 左边有序序列的初始索引 , middle 中间索引 , right 右边索引 , temp 中转数组]
    * @return: void
    * @Author: Mr.Mahongbin
    * @Date: 2019/10/3
    */
    public static void merger(int arr[], int left, int middle, int right, int[] temp){
        int i = left ;      // 初始化 i, 左边有序序列的 初始索引；
        int j = middle + 1; // 初始化 j, 右边有序序列的 初始索引；
        int t = 0 ;         // 指向 temp 数组的当前索引；

        // 一： 先把左右两边(有序) 的数据按照规则填充到temp数组，直到 左右两边的有序序列，有一边处理完毕为止；
        while(i <= middle && j <= right){
            // 如果左边的有序序列的当前 元素，<= 右边有序序列的当前元素，即将左边当前元素 拷贝到 temp数组， 然后 t++ ,i++
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else{  // 反之，将右边有序序列 当前元素， 填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // 二：把有剩余数据的一边的数据 依次全部填充到temp
        while( i <= middle){   // 左边有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while( j <= right){   // 右边有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[j] ;
            t += 1;
            j += 1;
        }

        // 三：将temp 数组的元素拷贝到 arr;
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right ){
            arr[tempLeft] = temp[t] ;
            t += 1;
            tempLeft += 1;
        }


    }
}
