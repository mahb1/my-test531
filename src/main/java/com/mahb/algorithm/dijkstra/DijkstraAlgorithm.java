package com.mahb.algorithm.dijkstra;


import java.util.Arrays;

/**
 * @program: algorithm
 * @description: 迪杰斯特拉算法
 * @author: Ma hong bin
 * @create: 2019-10-08 12:40
 **/
public class DijkstraAlgorithm {

    public static void main(String[] args) {

        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];

        final int N = 65535 ; // 表示不可链接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);

        graph.showGraph();

        graph.dsj(2); // 指定下标开始

        graph.showDjs();

    }

}

// 已访问顶点集合
class VisitedVertex{

    // 记录各个顶点是否访问过， 1 表示访问过，0 未访问，会动态更新
    public int[] already_arr;
    // 每个下标对应的值 为前一个顶点下标， 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他 所有顶点的 距离，比如G 为出发顶点， 就会记录 G 到其他顶点到距离，会动态更新，求的最短距离会存放到 dis
    public int[] dis;

    // length 为顶点的个数， index 为出发顶点对应的下标
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length] ;
        this.pre_visited = new int[length] ;
        this.dis = new int[length] ;

        // 初始化
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1; // 设置出发顶点被访问过；
        this.dis[index] = 0; // 设置 出发顶点的访问距离为 0；
    }

    // 判断 index 顶点是否被访问过；访问过 返回true，否则 返回false；
    public boolean in(int index){
        return already_arr[index] ==1;
    }

    // 更新出发顶点到 index 顶点的 距离
    // 因为现在只有一层，如果处理的 有多层， 需要把顶点到前驱节点， 以及 前驱节点到顶点到距离 相加 来判断是否小于前边到距离
    public void updateDis (int index, int len){
        dis[index] = len;
    }

    // 更新 pre 顶点的前驱顶点 为 index顶点
    public void updatePre (int pre, int index){
        pre_visited[pre] = index;
    }

    // 返回出发顶点到 index顶点的 距离
    public int getDis(int index){
        return dis[index] ;
    }

    // 继续选择并返回新的访问顶点，比如这里的G 完后，就是A 点作为新的访问顶点（注意，不是出发顶点）
    public int updateArr(){
        int min = 65535, index = 0;

        for(int i=0; i< already_arr.length; i++){
            if(already_arr[i] == 0 && dis[i] < min) {  // i 顶点没有被访问过 并且 下标对应的距离 小于 min ;
                min = dis[i];
                index = i;
            }
        }
        // 更新index 顶点被访问过；
        already_arr[index] = 1;
        return  index;
    }


    // 输出三个数组的 情况
    public void show(){
        System.out.println("========================");
        for(int i : already_arr){
            System.out.print(i+" ");
        }
        System.out.println("");

        for(int i : pre_visited){
            System.out.print(i+" ");
        }

        System.out.println("");
        for(int i : dis){
            System.out.print(i+" ");
        }

        System.out.println("");

        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for(int i : dis){
            if(i != 65535){
                System.out.print(vertex[count] +"("+i+")");
            }else{
                System.out.print("N ");
            }
            count++;
        }
        System.out.println("");
    }


}


class Graph{

    private char[] vertex; // 顶点数组
    private int[][] matrix; // 临接矩阵
    private VisitedVertex vv =null; // 已经访问顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 显示图
    public void showGraph(){
        for (int[] link : matrix){ System.out.println(Arrays.toString(link)); }
    }

    public void showDjs(){ vv.show(); }

    // 迪杰斯特拉算法实现;  index 表示出发顶点的下标
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index); // 更新index 顶点到 周围顶点的距离 和 前驱顶点；

        for(int j=1; j<vertex.length; j++){
            index = vv.updateArr(); // 选择并返回 新的访问顶点；
            update(index);  // 更新 index 顶点到周围顶点到 距离和 前驱顶点；
        }
    }

    // 更新index下标顶点到周围 顶点的距离 和周围顶点到前驱顶点
    public void  update(int index){
        int len = 0;

        // 根据遍历我们的临接矩阵的 matrix[index] 行
        for(int j=0; j< matrix[index].length; j++){

            // len 含义是： 出发顶点到index 顶点的距离 + 从index顶点到 j 顶点到 距离到和； 广度优先算法模式；
            len = vv.getDis(index) + matrix[index][j];

            // 如果j 顶点没有被访问过， 并且len 小于出发顶点到 j 顶点的距离， 就需要更新；
            if(!vv.in(j) && len < vv.getDis(j)){
                vv.updatePre(j, index);   // 更新 j 顶点的前驱为 index 顶点；
                vv.updateDis(j, len);   // 更新出发顶点到 j 顶点的距离
            }
        }
    }

}

