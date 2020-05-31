package com.mahb.algorithm.tree;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-04 14:08
 **/
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder();

    }
}

class ArrBinaryTree{
    private int[] arr ;
    public ArrBinaryTree(int[] arr) { this.arr = arr; }

    public void preOrder(){
        this.preOrder(0);
    }
    // 顺序存储 二叉树的前序遍历 ;  index 数组下标
    public void preOrder(int index) {
        if(arr == null || arr.length==0){ System.out.println("数组为空，不能按照二叉树前序遍历！"); }
        System.out.println("当前元素为：" + arr[index]);
        // 向左递归
        if((index * 2 +1) < arr.length){
            preOrder(2*index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < arr.length){
            preOrder(index * 2 + 2);
        }
    }

    //  中序 和 后序 自己实现

}


