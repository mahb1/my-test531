package com.mahb.algorithm.prim;

import com.mahb.algorithm.graph.Graph;
import javafx.beans.WeakInvalidationListener;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-06 11:59
 **/
public class PrimAlgorithm {

    public static void main(String[] args) {

        // 看看图 是否创建成功
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);

        minTree.showGraph(mGraph);
        minTree.prim(mGraph, 0);

    }
}
// 创建最小生成树
class MinTree{
    // 创建图的 邻接矩阵
    /**
    * @Description:
    * @Param: [ mGraph 图对象, verxs 图对应的顶点的个数 , data 图的各个顶点的值 , weight 图的 邻接矩阵 ]
    * @return: void
    * @Author: Mr.Mahongbin
    * @Date: 2019/10/6
    */
    public void createGraph(MGraph mGraph, int verxs, char[] data , int[][] weight){
        int i, j ;
        for(i=0; i< verxs; i++){
            mGraph.data[i] = data[i];
            for(j = 0; j<verxs; j++){
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph mGraph){
        for(int[] link : mGraph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //  v 表示 图的第几个顶点开始生成 'A'-> 0 'B' -> 1
    public void prim(MGraph graph, int v){

        int visited[] = new int[graph.verx] ; // 标记节点(顶点) ， 是否被访问过
        // 把 当前的节点标记 为已 访问
        visited[v] = 1;

        // h1 和 h2  记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000 ; // 用来在后面的 遍历过程中， 会被替换；

        // 因为有 graph.verxs 顶点，普里姆算法结束后， 有 graph.verxs -1 边；
        for(int k =1; k<graph.verx; k++){

            // 这个是 确定每一次 生成的子图， 和那个 节点的 距离 最近；
            for(int i=0; i< graph.verx; i++){       // i 节点表示 被访问过的
                for(int j=0; j< graph.verx; j++){   // j 节点表示 没有 被访问过

                    if(visited[i] == 1 && visited[j] == 0 &&
                        graph.weight[i][j] < minWeight){
                        // 寻找已 访问过的节点 和 未访问过的节点间的 权值 最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i ;
                        h2 = j ;
                    }
                }
            }

            // 找到 一条边是最小的
            System.out.println(" 边 < "+ graph.data[h1] + "," + graph.data[h2] + " > 权值 :"+ minWeight );
            visited[h2] = 1 ; // 将当前节点 标记 已经访问
            minWeight = 10000; // 重置为 最大值；
        }


    }

}


class MGraph{
    int verx ;      // 表示图的 节点个数
    char[] data ;  // 存方节点 数据
    int[][] weight ;// 存放边，邻接矩阵

    public MGraph(int verxs){
        this.verx = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }





}
