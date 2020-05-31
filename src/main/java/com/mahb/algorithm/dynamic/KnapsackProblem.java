package com.mahb.algorithm.dynamic;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-06 08:18
 **/
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w = {1,4,3};   // 物品重量
        int[] val = {1500, 3000, 2000}; // 物品价值
        int m = 4;           //  背包容量
        int n = val.length;  // 物品个数
        int[][] path = new int[n+1][m+1];  // 记录放入商品的情况；

        // v[i][j] 表示在 第 i 个物品中 能够装入容量 为j 的背包中的 最大价值
        int[][] v = new int[n+1][m+1] ;

        // 初始化第一行和第一列， 为 0 ；
        for(int i=0 ; i< v.length; i++){
            v[i][0] = 0; // 将第一列 设置为 0；
        }
        for(int i=0; i< v[0].length; i++){
            v[0][i] = 0;  // 将第一行设置 0；
        }

        // 根据公式 动态规划处理
        for(int i = 1; i< v.length; i++){       // 不处理第一行
            for(int j= 1; j<v[0].length; j++){  // 不处理第一列

               // 当准备加入新增的 商品的容量 大于当前背包的容量时，就直接使用上一个单元格装入策略；v[i-1][j] 表示上一个单元格内的价值
                if(w[i-1] > j){
                    v[i][j] = v[i-1][j] ;
                }else{

                    // 当准备加入的新增的商品的 容量小于等于当前背包的容量时 ,max 获取 那个价值最大的；
                    // v[i][j] = Math.max( v[i-1][j], val[i-1]+v[i-1][j-w[i-1]] );

                    if(v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                       v[i][j] = val[i-1]+v[i-1][j-w[i-1]] ;
                       path[i][j] = 1; // 记录最优情况；
                    }else{
                        v[i][j] = v[i-1][j] ;
                    }
                }
            }
        }

        // 输出 v
        for(int i=0 ; i< v.length; i++){
            for(int j=0; j< v[i].length; j++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("===========================================================");

        // 输出放入的那些商品
       /* for(int i =0; i< path.length; i++){
            for(int j=0; j< path[i].length ; j++){
                if(path[i][j] == 1){ System.out.println("第 "+i+" 个商品 放入背包"); }
            }
        }*/

        int i= path.length -1;      // 行 最大下标
        int j= path[0].length - 1;  // 列 最大下标
        while( i > 0 && j > 0){  // 从 path 的最后开始找
            if(path[i][j] == 1){
                System.out.println("第 "+i+" 个商品 放入背包");
                j -= w[i-1];
            }
            i--;
        }

    }
}
