package com.mahb.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @program: algorithm
 * @description:    骑士周游
 * @author: Ma hong bin
 * @create: 2019-10-13 22:49
 **/
public class HorseChessborad {

    private static int X; // 棋盘 列
    private static int Y; // 棋盘 行

        private static boolean visited[]; // 一维数组，累加行的位置， 标记棋盘的各个位置是否被访问过；
    private static boolean finished; // 标记是否棋盘的所有位置 都被访问过；

    public static void main(String[] args) {

        System.out.println("骑士周游开始执行..........");
        X = 8;
        Y = 8;
        int row = 1; // 马初始位置 行，从 1 开始编号；
        int col = 1; // 马初始位置 列，从 1 开始编号；

        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard,row-1,col-1,1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end-start) + "毫秒！");

        // 输出
        for(int[] r : chessboard){
            for(int step : r){
                System.out.print(step + "\t");
            }
            System.out.println("");
        }
    }


    /**
    * @Description:   马踏棋盘，骑士周游的算法 ；回溯实现；
    * @Param: [chessboard, row, col, step 表示第几步；]
    * @return: void
    * @Author: Ma hongbin
    * @Date: 2019-10-14
    */
    public static void traversalChessboard(int[][] chessboard , int row ,int col, int step){

        chessboard[row][col] = step;
        visited[row * X + col] = true; // 该位置已被访问；

        ArrayList<Point> ps = next(new Point(col,row));

        // 对 ps 的所有 point 对象 的下一步位置 的数目，进行非递减排序；
        // sort(ps);

        // 遍历
        while (!ps.isEmpty()){
            Point p = ps.remove(0); // 取出一个可以走的位置；

            // 判断该点是否已经访问过
            if(!visited[p.y * X + p.x ]){ // 没有访问过时
                traversalChessboard(chessboard, p.y, p.x, step+1); // 回溯
            }
        }


        // 判断马是否完成任务，使用 step 和 应该走的步数比较， 如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        // step < X * Y 有两种情况，1：棋盘到 目前位置， 仍然没有走完， 2：棋盘处于 一个回溯过程；
        if(step < X * Y && !finished){
            chessboard[row][col] = 0 ;
            visited[row * X + col] = false ;
        }else{
            finished = true ;
        }

    }


    /**
    * @Description: 判断可走的步数
    * @Param: [curPoint]
    * @return: java.util.ArrayList<java.awt.Point>
    * @Author: Ma hongbin
    * @Date: 2019-10-14
    */
    public static ArrayList<Point> next(Point curPoint){

        ArrayList<Point> ps = new ArrayList<Point>();
        Point p1 = new Point();

        // curPoint.x -2 表示向左移动两位， + 表示向右， curPoint.y -1 表示向上移动一位，向下用 +
        // 走到 5 的位置， 添加到集合；
        if((p1.x = curPoint.x -2) >= 0 && (p1.y = curPoint.y -1) >= 0){
            ps.add(new Point(p1));
        }

        // 如果马 可以到 6 的位置；
        if((p1.x = curPoint.x -1) >= 0 && (p1.y = curPoint.y - 2) >=0){
            ps.add(new Point(p1));
        }

        // 如果马 可以到 7 的位置；
        if((p1.x = curPoint.x +1) < X && (p1.y = curPoint.y - 2) >=0){
            ps.add(new Point(p1));
        }

        // 如果马 可以到 0 的位置；
        if((p1.x = curPoint.x +2) < X && (p1.y = curPoint.y - 1) >=0){
            ps.add(new Point(p1));
        }

        // 如果马 可以到 1 的位置；
        if((p1.x = curPoint.x +2) < X && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        // 如果马 可以到 2 的位置；
        if((p1.x = curPoint.x +1) < X && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }

        // 如果马 可以到 3 的位置；
        if((p1.x = curPoint.x -1) >= 0 && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }

        // 如果马 可以到 4 的位置；
        if((p1.x = curPoint.x -2) >= 0 && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }

        return ps ;
    }


    /**
    * @Description: 采用贪心算法进行优化， 根据当前这一步的所有下一步的选择位置，进行非递减排序, 减少回溯;
     *  选择下一步可执行的 最小选择; 贪心就是选择最优的步骤；
    * @Param: [ps]
    * @return: void
    * @Author: Ma hongbin
    * @Date: 2019-10-14
    */
    public static void sort(ArrayList<Point> ps){

        // 排序
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {

                // 获取到 o1 下一步的 所有位置个数
                int count1 = next(o1).size();

                // 获取到 o2 下一步的 所有位置个数
                int count2 = next(o2).size();


                if(count1 < count2){
                    return  -1;
                }else if(count1 == count2 ){
                    return 0 ;
                }else {
                    return 1;
                }
            }
        });
    }


}





