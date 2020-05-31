package com.mahb.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-05 19:48
 **/
public class Graph {

    private ArrayList<String> vertexList ; // 存储顶点集合
    private int[][] edges ;       //  存储图对应的 邻结矩阵
    private int numOfEdges;     //  表示边的数目
    private boolean[] isVisited ; // 记录某个节点是否被访问过

    public static void main(String[] args) {

        int n = 8; // 节点 的个数
        // String  vertexValue[] = {"A","B","C","D","E"};
        String  vertexValue[] = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(n);
        for(String value : vertexValue){
            graph.insertVertex(value);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        // graph.insertEdge(5,6,1);

        // 显示一把邻接矩阵
        graph.showGraph();

         System.out.println("深度遍历：");
         graph.dfs();

        System.out.println("广度遍历：");
        graph.bfs();

    }

    // 构造器， 进行初始化
    public Graph(int n){
        edges = new int[n][n] ;
        vertexList = new ArrayList<>();
        numOfEdges = 0 ;
    }

    // 得到第一个邻接节点的 下标 w
    public int getFirstNeighbor(int index){
        for(int j=0; j< vertexList.size(); j++){
            if(edges[index][j] > 0){
                return j ;
            }
        }
        return -1 ;
    }

    //  根据前一个邻接节点的下标 来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for(int j =v2 +1 ; j< vertexList.size() ; j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return  -1 ;
    }

    /***
    * @Description: 广度优先搜索
     * 步骤：
     *      1：访问初始节点v 并标记节点 v 为已访问；
     *      2：节点 v 入队列； 3：当队列非空时，继续执行， 否则算法结束；
     *      4：出队列，取得队头节点 u； 5：查找节点 u 的第一个 邻接节点 w；
     *      6：若节点 u 的邻接节点 w 不存在，则转到 步骤3，否则循环执行 以下三个步骤；
     *          6.1：若节点 w 尚未被访问，则访问节点 w 并标记 为 已访问；
     *          6.2：节点 w 入队列；
     *          6.3：查找节点 u 的继 w 邻接节点后的 下一个 邻接节点w ， 转到步骤 6；
     *
    * @Param: [] 对一个节点进行 广度优先遍历， 多个节点加个 for 循环
    * @return: void
    * @Author: Mr.Mahongbin
    * @Date: 2019/10/5
    */
    public void bfs(boolean[] isVisited, int i){
        int u ; // 表示 队列的 头节点对应的下标
        int w ; // 邻接节点 w;

        LinkedList queue = new LinkedList(); // 队列， 记录节点访问的 顺序
        System.out.print(getValueByIndex(i) + "==>");
        isVisited[i] = true;  // 节点已访问
        queue.addLast(i);   // 将节点加入队列；

        while (!queue.isEmpty()){

            u = (Integer) queue.removeFirst();  // 取出队列头节点下标
            w = getFirstNeighbor(u);  // 得到 第一个邻接节点的 下标 w
            while( w != -1) {   // 找到
                if( !isVisited[w]){   // 没有 访问过
                    System.out.print(getValueByIndex(w) + "==>");
                    isVisited[w] = true;  // 标记已经访问
                    queue.addLast(w);
                }
                // 以 u 为 前驱点，找 w 后面的 下一个邻接点；
                w = getNextNeighbor(u, w) ; // 体现 广度优先；

            }
        }
    }

    // 遍历所有节点，都进行 广度优先搜索
    public void bfs(){

        isVisited = new boolean[vertexList.size()];
        for(int i=0; i< getNumOfVertex(); i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**
    * @Description:  深度优先 遍历
     *  步骤：
     *      1：访问初始节点 v，并标记节点 v 已访问;
     *      2：查找节点 v 的第一个邻接节点 w;
     *      3: 若 w 存在，则继续执行 4 ，如果 w 不存在，则回到第1步，将从 v 的下一个节点继续 ；
     *      4：若 w 未被访问，对 w 进行深度优先 遍历递归 ( 即把w 当做 另一个 v，然后进行 步骤 1 2 3)
     *      5: 查找节点 v 的 w 邻接节点的 下一个邻接节点， 转到 步骤 3 ;
    * @Param: [i]
    * @return: java.lang.String
    * @Author: Mr.Mahongbin
    * @Date: 2019/10/5
    */
    public void dfs(boolean[] isVisited, int i){
        System.out.print(getValueByIndex(i) + "-->");

        isVisited[i] = true;           // 将节点设置为 已经访问
        int w = getFirstNeighbor(i) ; // 查找节点 i 的第一个邻接节点

        while (w != -1){ // 说明有
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            // 如果 w 节点 已经被访问过， 获取下一个
            w = getNextNeighbor(i, w);
        }
    }

    // 对 dfs 重载，遍历我们所有的 节点，并进行 dfs；
    public void dfs(){

        isVisited = new boolean[vertexList.size()];
        // 遍历所有节点，进行 dfs 【回溯】
        System.out.println("getNumOfEdges()....."+getNumOfEdges());
        for(int i=0; i< getNumOfEdges(); i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    // 返回节点i (下标) 对应的数据
    public String getValueByIndex(int i){ return vertexList.get(i); }

    // 返回 v1 v2 的权值
    public int getWeight(int v1, int v2){ return edges[v1][v2]; }

    // 显示 图对应的 矩阵；
    public void showGraph(){
        for(int[] link : edges){
            System.err.println(Arrays.toString(link));
        }
    }

    // 插入节点
    public void insertVertex(String vertex){ vertexList.add(vertex) ; }

    // 返回节点的 个数
    public int getNumOfVertex(){ return vertexList.size(); }
    // 得到边的 数目
     public int getNumOfEdges(){ return numOfEdges; }


    //  添加边 : [v1 表示点的下标 即是第几个顶点 , v2 第二个顶点对应 的下标, weight 权值 ]
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight ;
        edges[v2][v1] = weight ;
        numOfEdges ++;
    }


}
