package com.mahb.algorithm.search;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:  黄金分割  查找法
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 20:35
 **/
public class FibonacciSearch {

    public static void main(String[] args) {

        int[] arr = {1,8,10,89,1000,1234};
        int re = fibSearch(arr,789) ;
        System.out.println(re);
    }

    // mid = low+F(k-1) -1 , 需要使用 斐波那契数列；此方法生成
    public static int[] fib(){
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for(int i=2; i<20; i++){
            f[i] = f[i-1] +f[i-2];
        }
        return f;
    }

    public static int fibSearch(int[] arr, int key){
        int low = 0;
        int hight = arr.length -1;
        int k = 0 ; // 斐波那契 分割数值的 下标
        int mid = 0 ;

        int[] f= fib();
        // 获取 斐波那契分割数值的下标
        while(hight > f[k] -1 ){
            k++ ;
        }

        // 因为 f[k] 值可能大于 arr 的长度，因此需要使用 Arrays 类构造新的数组， 并指向 arr[] ；
        // 不足部分使用 0 填充；
        int[] temp = Arrays.copyOf(arr, f[k]) ;

        // 实际上需要使用 arr  数组最后的数 填充 到temp；
        for(int i= hight+1 ; i< temp.length; i++){
            temp[i] = arr[hight];
        }

        while(low < hight){
            mid = low+ f[k - 1] + 1 ;
            if(key < temp[mid]){  // 向左查找
                hight = mid - 1;
                // 说明：1： 全部元素= 前面元素 + 后面元素；   f[k] = f[k - 1] + f[k - 2]
                //       2： 前面有 f[k - 1] 个元素，所以可以继续分 f[k -1 ] = f[k - 2] + f[k - 3]
                //       3： 所以在 f[k - 1] 的前面继续查找 k --， 即下次循环：mid = f[k - 1 -1 ] - 1 ;
                k-- ;
            } else   if(key > temp[mid]) {  // 向右查找
                low = mid + 1;
                // 即在 f[k - 2] 的前面 进行查找 k -= 2 ， 下次循环为：mid = f[k-1-2] -1 ;
                k -= 2;
            } else{  // 表示找到
                if(mid <= hight){
                    return mid;
                }else{
                    return hight;
                }
            }
        }
        return -1;
    }
}
