package com.mahb.algorithm.kmp;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-06 08:53
 **/
public class ViolenceMatch {
    public static void main(String[] args) {

        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int index = violenceMatch(str1, str2);
        System.out.println(" index = " + index );

    }


    public static int violenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while(i < s1Len && j < s2Len){  // 保证匹配时， 不越界
            if(s1[i] == s2[j]){  // 匹配 ok
                i++ ;
                j++ ;
            }else {
                i = i - (j-1);
                j = 0;
            }
        }

        if(j == s2Len){  // 匹配成功， 返回下标
            return i-j ;
        }else{
            return  -1;
        }
    }
}
