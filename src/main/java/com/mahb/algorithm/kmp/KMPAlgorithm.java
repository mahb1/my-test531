package com.mahb.algorithm.kmp;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-06 09:04
 *   网上写得好的 KMP 算法
 *   https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
 **/
public class KMPAlgorithm {

    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[]  next = kmpNext("ABCDABD");
        int  index = kmpSearch(str1,str2,next);

        System.out.println("newx = " + Arrays.toString(next) );
        System.out.println("index = " + index );
    }

    // KMP 搜索算法
    public static int kmpSearch(String str1, String str2, int[] next){

        for(int i=0 ,j=0 ; i< str1.length(); i++){

            // 处理  str1.charAt(i) != str2.charAt(j) 不相等时  ; KMP  算法核心点  好好理解
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1] ;
            }

            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if( j == str2.length()){
                return  i-j+1;
            }
        }
        return  -1;
    }

    // 获取字串的 部分匹配值 的匹配表
    public static int[] kmpNext (String dest){
        int[] next = new int[dest.length()] ;// next 数组保存部分匹配值
        next[0] = 0; // 如果字符串长度是 1 ，部分匹配值就是 0；

        for(int i=1, j = 0 ; i<dest.length(); i++){

            //  dest.charAt(i) != dest.charAt(j) 表示 不满足时，需要从 next[ j -1] 获取更新的j ，
            //  直到我们发现 dest.charAt(i) == dest.charAt(j) 满足时，才会退出
            // 此处是 KMP 算法的 核心点
            while( j > 0 &&   dest.charAt(i) != dest.charAt(j)){
                j =  next[j-1] ;
            }

            if(dest.charAt(i) == dest.charAt(j)){ // 满足时，  部分匹配值就是 +1 ;
                j++;
            }
            next[i] = j ;
        }
        return  next ;
    }

}
