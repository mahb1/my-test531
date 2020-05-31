package com.mahb.algorithm.search;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 18:24
 **/
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1,64,-1,89,11,9};
        int result =  seqSearch(arr,89);
        if(result == -1){
            System.out.println(" 没有找到。。。");
        }else{
            System.out.println("找到了， 位置是："+result);
        }
    }

    public static int seqSearch(int[] arr,int value){
        for(int i=0; i<arr.length; i++){
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
