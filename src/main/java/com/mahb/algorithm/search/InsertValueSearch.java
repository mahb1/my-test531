package com.mahb.algorithm.search;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 20:02
 **/
public class InsertValueSearch {
    public static void main(String[] args) {

        int[] arr = {10,15,19,28,30,36,38,48,48,48,57,59,66,77,79,88,90};
//        int[] arr = new int[100] ;
//        for(int i=0; i<arr.length; i++){
//            arr[i] = i+1;
//        }
        int result = insertValueSearch(arr,0, arr.length -1, 48);
        System.out.println(result);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findValue){
        System.out.println("查找次数---------");
        if(left > right || findValue < arr[0] || findValue > arr[arr.length-1]){ return -1; }

        // 得到 插入的值， 该公式表示，找到当前需要找的 findvalue值 的 下标或是，最近下标 (从左到右) ；
        int mid = left + (right-left) * (findValue - arr[left]) / (arr[right] - arr[left]) ;
        int midValue = arr[mid];

        if(findValue > midValue){
            return insertValueSearch(arr,mid+1, right,findValue) ;
        } else if(findValue < midValue){
            return insertValueSearch(arr,left, mid-1,findValue) ;
        }else{
            return mid;
        }
    }
}
