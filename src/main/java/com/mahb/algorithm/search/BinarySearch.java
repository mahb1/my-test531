package com.mahb.algorithm.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: algorithm
 * @description: 二分查找的前提，必须是有序的； 递归实现；
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 18:37
 **/
public class BinarySearch {
    public static void main(String[] args) {

        int arr[] = {1,8,10,89,1000,1000,1000,1256};
        // int resIndex= binarySearch(arr,0, arr.length, 1000);
       // System.out.println("找到的 索引：" + resIndex);

        List<Integer> result = binarySearch2(arr,0, arr.length, 1000);
        if(result != null){
            System.out.println(result.toString());
        }
    }

    //  查找第一位出现的 对应的 value值 ；
    public static int binarySearch(int[] arr, int left, int right, int findValue){

        // 当 left > right ，说明递归整个数组，没有找到值；
        if(left > right){
            return  -1;
        }

        int mid = (left+right)/2;
        int midVal = arr[mid];

        if(findValue > midVal){
            return binarySearch(arr,mid+1, right,findValue);
        }else if(findValue < midVal){
            return binarySearch(arr,left, mid-1,findValue);
        }else{
            return mid;
        }
    }

    // 将找到的所有 等于 value的值 存入集合 并返回
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue){
        if(left > right){
            return  null;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];

        if(findValue > midVal){
            return binarySearch2(arr,mid+1, right,findValue);
        }else if(findValue < midVal){
            return binarySearch2(arr,left, mid-1,findValue);
        }else{
            // 找到对应的值后， 需要进行向左， 或是 向右继续查找是否存在 相同 的值
            List<Integer> resIndexList = new ArrayList<>();
            int temp = mid -1;
            while (true){
                if(temp <0 || arr[temp] != findValue){
                    break;
                }else {
                    resIndexList.add(temp);
                    temp -= 1; // 左移
                }
            }
            resIndexList.add(mid); // 将中间的加入

            temp = mid + 1;
            while (true){
                if(temp > arr.length -1 || arr[temp] != findValue){
                    break;
                }else{
                    resIndexList.add(temp);
                    temp += 1 ; // 右移
                }
            }
            return resIndexList ;
        }
    }
}
