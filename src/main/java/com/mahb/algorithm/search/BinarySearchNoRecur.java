package com.mahb.algorithm.search;

import sun.awt.dnd.SunDragSourceContextPeer;
import sun.reflect.generics.tree.Tree;

/**
 * @program: algorithm
 * @description: 非递归实现
 * @author: Mr.Mahongbin
 * @create: 2019-10-05 22:35
 **/
public class BinarySearchNoRecur {

    public static void main(String[] args) {

        int[] arr = {1,3,10,11,67,100};
        int index = binarySearch(arr, 100);
        System.out.println(index);

    }

    // 二分查找的 非递归实现
    public static int binarySearch(int[] arr, int target){

        int left = 0;
        int right = arr.length -1 ;
        while( left <= right){
            int mid = (left + right) /2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid -1; // 需要向左边查找
            } else{
                left = mid +1 ; // 需要向右查找
            }
        }
        return  -1;

    }

}
