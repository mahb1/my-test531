package com.mahb.algorithm.floyd;

import sun.applet.Main;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:  弗洛伊德算法
 * @author: Ma hong bin
 * @create: 2019-10-13 21:57
 **/
public class FloydAlgorithm {

    public static void main(String[] args) {

        char[] vertex = {'A','B','C','D','E','F','G'};

        // 邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];

        final int N = 65535;

        matrix[0] = new int[] {0, 5, 7, N, N, N, 2};
        matrix[1] = new int[] {5, 0, N, 9, N, N, 3};
        matrix[2] = new int[] {7, N, 0, N, 8, N, N};
        matrix[3] = new int[] {N, 9, N, 0, N, 4, 2};
        matrix[4] = new int[] {N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[] {N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[] {2, 3, N, N, 4, 6, 6};

        Graph graph = new Graph(vertex.length, matrix, vertex);

        graph.floyd();
        graph.show();
    }

}

class Graph{
    private char[] vertex ;
    private int[][] dis ;  // 保存从各个顶点出发到其他顶点到距离，最后的结果，也保留在该数组
    private int[][] pre ;  // 保存到达目标到前驱顶点

    // length 大小 ；  martix 邻接矩阵 ；  vertex 顶点数组；
    public Graph(int length, int[][] martix, char[] vertex) {
        this.vertex = vertex;
        this.dis = martix;
        this.pre = new int[length][length];

        // 对 pre 数组初始化，注意存放的是 前驱顶点的下标
        for (int i=0; i< length; i++){
            Arrays.fill(pre[i] ,i);
        }
    }

    // 显示 dis 数组 pre 数组
    public void show(){

        char[] vertex = {'A','B','C','D','E','F','G'};

        for (int k =0; k<dis.length; k++){

            // 输出 pre 数组的一行
            for (int i=0; i<dis.length; i++){
                System.out.print( vertex[pre[k][i]] +" ");
            }
            System.out.println("");
            // 输出 dis 数组的一行
            for (int i=0; i<dis.length; i++){
                System.out.print( vertex[k]+"->" +vertex[i]+" shot："+ dis[k][i] +"==");
            }
            System.out.println("");
            System.out.println("");
        }
    }

    //
    public void floyd(){
       int len = 0; // 变量保存距离；

        // 对中间节点的遍历， k 就是中间顶点的下标
       for(int k=0; k<dis.length; k++){

           // 从 i 顶点开始出发；
           for(int i=0; i< dis.length ; i++){

               // j 就是到达的最终顶点；
               for(int j=0; j< dis.length; j++){

                   len = dis[i][k] + dis[k][j]; // =》 求出从 i 顶点出发， 经过 k 中间顶点， 到达 j 顶点距离；
                   if(len < dis[i][j]){         // 如果 len 小于dis[i][j]
                       dis[i][j] = len;         // 更新距离
                       pre[i][j] = pre[k][j];   // 更新前驱顶点；
                   }
               }
           }
       }
    }

}

