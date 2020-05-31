package com.mahb.algorithm.kruska;

import sun.security.krb5.internal.KrbCredInfo;
import sun.security.krb5.internal.TGSRep;
import sun.security.x509.EDIPartyName;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program: algorithm
 * @description:  克鲁斯卡尔
 * @author: Mr.Mahongbin
 * @create: 2019-10-06 22:47
 **/
public class KruskalCase {

    private int edgeNum ;    // 边的个数
    private char[] vertexs;  // 顶点数组
    private int[][] matrix;  // 邻接矩阵

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        char[] vertexs = {'A','B','C','D','E','F','G'} ;
        int matrix[][] = {
                {0,    12,   INF,  INF,  INF,  16,  14},
                {12,   0,    10,   INF,  INF,  7,   INF},
                {INF,  10,   0,     3,    5,   6,   INF},
                {INF,  INF,  3,     0,    4,   INF,  INF},
                {INF,  INF,  5,     4,    0,    2,   8},
                {16,   7,    6,    INF,   2,    0,   9},
                {14,   INF,  INF,  INF,   8,    9,   0}};
        KruskalCase kcase = new KruskalCase(vertexs, matrix);
        kcase.print();
        EData[] edges = kcase.getEdges();
        //System.out.println("xx==" + Arrays.toString(edges));
        kcase.sortEdge(edges);
        //System.out.println(""+ Arrays.toString(edges));
        kcase.kruskal();

    }

    public KruskalCase(char[] vertexs, int[][] matrix){
        int vlen = vertexs.length;      // 初始化顶点数和边的个数
        this.vertexs = new char[vlen] ; // 初始化顶点

        // 初始化顶点， 复制拷贝方式
        for(int i=0 ; i<vertexs.length ; i ++){
            this.vertexs[i] = vertexs[i];
        }
        this.matrix = new int[vlen][vlen] ;  // 初始化边, 复制拷贝方式
        for(int i = 0; i< vlen; i++){
            for(int j = 0; j < vlen; j++){
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for(int i = 0; i< vlen; i++){
            for(int j = i+1; j < vlen; j++){
                if(this.matrix[i][j] != INF){  // 说明有效
                    edgeNum ++ ;
                }
            }
        }

    }

    public void kruskal(){
        int index= 0; // 最后结果数组的 索引
        int[] ends = new int[edgeNum]; // 保存 "已有最小生成树" 中的每个顶点在 最小生成树中的 终点
        // 创建结果数组， 保存最后的 最小生成树；
        EData[] rets = new EData[edgeNum] ;

        //获取图中 所有的边的 集合， 一共有 12边
        EData[] edges = getEdges();
        System.out.println("图的边的集合："+Arrays.toString(edges) +"， 共" + edges.length +"条边；");

        // 按照边的权值 大小进行排序 (从小到大)
        sortEdge(edges);

        // 遍历 edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，没有就加入 rets，否则不能加入；
        for(int i=0; i< edgeNum; i++){
            int p1 = getPosition(edges[i].start);   // 获取到 第 i 条边的第一个顶点
            int p2 = getPosition(edges[i].end) ;    // 获取到 第 i 条边的第二个顶点

            int m = getEnd(ends, p1);  // 获取 p1 这个顶点 在已有最小生成树中的 终点；
            int n = getEnd(ends, p2);  // 获取 p2 这个顶点 在已有最小生成树中的 终点；

            if(m != n){ // 没有构成回路
                ends[m] = n;  // 设置 m 在 "已有最小生成树" 的 终点；
                rets[index++] = edges[i]; // 有一条边 加入到 rets 数组 ；
            }
        }
        System.out.println("最小生成树为：");
        for(int i=0; i< index; i++){
            System.out.println(rets[i]) ;

        }
    }

    public void print(){
        System.out.println(" 邻接矩阵为： \n" );
        for(int i = 0; i< vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // ch 表示顶点， 返回的 是该顶点对应的 下标
    private int getPosition(char ch){
        for(int i=0 ; i< vertexs.length; i++){
            if(vertexs[i] == ch) {  // 找到
                return i;
            }
        }
        return  -1;
    }

    // 获取图中的 边，放到 EData 数组中，然后遍历； 通过matrix 邻接矩阵来获取；
    private EData[] getEdges (){
        int index = 0;
        EData[] edges = new EData[edgeNum]; // 存储所有的边
        for(int i=0; i< vertexs.length; i++){
            for(int j = i+1; j<vertexs.length ; j++){
                if(matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    // 获取下标为 i 的顶点的终点，用于后面 判断两个顶点的终点是否相同；ends 数组就是记录了 各个顶点对应的终点是那个；
    // i 传入的顶点对应的下标，返回的就是 下标为i 这个顶点对应 的终点的下标；
    private int getEnd(int[] ends, int i){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    // 对边进行排序
    private void sortEdge(EData[] edges){
        for(int i=0 ; i< edges.length-1; i++){
            for(int j=0 ; j< edges.length-1; j++){
                if(edges[j].weight > edges[j+1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j+1] ;
                    edges[j+1] = tmp;
                }
            }
        }
    }


}

// 创建一个类 EDate ,它的对象 实例就表示 一条边
class EData{
    char start ; // 边的一个点
    char end ;   // 边的另外一个点
    int weight ; // 边 的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [ <" + start + ", " + end + " >=" + weight + '}'; }
}
