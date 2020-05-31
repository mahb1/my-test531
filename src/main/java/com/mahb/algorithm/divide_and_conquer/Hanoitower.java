package com.mahb.algorithm.divide_and_conquer;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-05 22:56
 **/
public class Hanoitower {

    public static void main(String[] args) {

        hanoitower(5, 'A','B','C');

    }

    // 汉诺塔 移动， 分治算法
    public static void hanoitower(int num, char a, char b , char c){
        if(num == 1){
            System.out.println(" 第 1 个盘从 " +a+"--> "+c);
        } else{
            //  1：先把最上面的 所有盘 A -> B ，移动过程 会使用到 c
            hanoitower(num-1, a, c, b);
            //  2：把最下边的 盘 A -> C
            System.out.println("第 " +num+ " 个盘从 " +a+ "-->" + c) ;
            //  3： 把 B 塔的所有盘 从移动 B --> C, 移动过程使用到 A 塔
            hanoitower(num -1 , b, a , c);

        }
    }
}
