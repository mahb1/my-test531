package com.mahb.algorithm.recursion;

import org.springframework.expression.spel.ast.QualifiedIdentifier;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-02 20:53
 *  八皇后游戏地址：  http://www.7k7k.com/swf/49842.htm
 **/
public class Queue8 {

    int max = 8;
    static int count =0;
    static int judgecount =0;
    // 八皇后问题，用一维数组解决： arr[8] = {0,4,7,5,2,6,1,3} ，对应 arr 下标 表示第几行的皇后，
    // arr[i] =val, val 表示 第i+1 个皇后 ，放在 i+1 列；
    int[] array = new int[max];
    public static void main(String[] args) {

        // 测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("共有 解法：。。。。。。"+count);
        System.out.println("共有 冲突：。。。。。。"+judgecount);
    }

    // 放置 第 n 个皇后
    // 特别注意： check 每次递归时，进入到check中都有   for(int i=0;i<max ; i++), 因此会有回溯
    private void check(int n){
        if(n == max){ print(); return; }

        // 依次放入皇后，并判断是否冲突；
        for(int i=0;i<max ; i++){
            // 先把当前皇后 n 放到该行的第 1 列；
            array[n] = i;
            if(judge(n)){ // 不冲突时;
                check(n+1);
            }
            // 如果冲突，就继续 执行 array[n] = i; 即将第 n个皇后，放置本行的后移的一个位置
        }
    }

    // 重点是 如何判断是否 不冲突
    // 查看当我们放置 第n 个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    // 在 一维数组中，第一个皇后从第一行开始，依次类推，但是列，则 当前的 n+1 值就表示该 行的列的 位置；
    private boolean judge(int n){
        judgecount++;
        for(int i=0; i<n; i++){
            if(array[i] == array[n]   // 表示 第n 个皇后 和 前面的 n-1 个皇后 是否在 同一列
                    || Math.abs(n-i) == Math.abs(array[n]-array[i])){   // 表示 第n 个皇后 和 前面的 n-1 个皇后 是否在 同一斜线上

                return false;
            }
        }
        return true;
    }

    // 写一个方法，可以将 皇后摆放的位置输出
    private void print(){
        count++;
        for(int i=0; i<array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
