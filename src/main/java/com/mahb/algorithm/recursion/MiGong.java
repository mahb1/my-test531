package com.mahb.algorithm.recursion;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-02 19:47
 **/
public class MiGong {
    public static void main(String[] args) {

        // 二维数组，模拟迷宫
        int[][] map = new int[8][7];
        // 上下全部 置为1
        for(int i=0; i<7; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部 置为1
        for(int i=0; i<7; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置路障
        map[3][1] = 1;
        map[3][2] = 1;
        // map[1][2] = 1;
        map[2][2] = 1;

        for(int i=0; i<8;i ++){
            for(int j=0; j<7; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------");
        // 使用 递归回溯 找路
        setWay(map,1,1);
        // 输出小球走过的 新的地图
        for(int i=0; i<8;i ++){
            for(int j=0; j<7; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }


    // 使用 递归回溯 来给小球找 路
    // 说明：
    // 1 ： map 表示地图 ； 2： i,j 表示从地图的那个位置开始出发 (1,1)
    // 3 ： 如果小球能到 map[6][5] 位置，则说明通路找到；
    // 4 ： 约定： 当 map[i][j] 为0 表示 该点 没有走过， 当为1 表示墙，为2 表示通路可以走，为3 表示该点已走过，但不通；
    // 5 ： 在 走迷宫时，需要确定一个 策略 (方法)  下->右->上->左，如果该点 走不通， 再 回溯；
    //  备注： 可以修改 走路策略， 如 上->右->下->左
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }else {
            if(map[i][j] == 0){ // 表示未走过
                // 下->右->上->左
                map[i][j] = 2; // 该点可以走通
                if(setWay(map, i+1,j)){  // 向下走
                    return true;
                } else if(setWay(map, i,j+1)){ // 向右走
                    return true;
                }else if(setWay(map, i-1,j)){ // 向上走
                    return true;
                }else if(setWay(map, i,j-1)){ // 向左走
                    return true;
                }else{
                    map[i][j] = 3; // 该点走不通
                    return false;
                }
            }else{   // map[i][j] != 0 , 可能是 1，2 ，3
                return false ;
            }
        }
    }
}
