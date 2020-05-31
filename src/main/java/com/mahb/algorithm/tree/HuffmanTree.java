package com.mahb.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-04 18:58
 **/
public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr = {13,7,8,3,29,6,1};
        Node node = createHuffmanTree(arr);
        preOrder(node);

    }

    public static void preOrder(Node node){
        if(node != null){
            node.preOrder();
        }else{
            System.out.println("空树，不能输出");
        }
    }

    public static Node createHuffmanTree(int[] arr){
        // 1 ：遍历 arr 数组，2 ： 将 arr 的每个元素构成一个Node. 3 ：将Node 放入到 ArrayList
        List<Node> nodes = new ArrayList<>();
        for(int value : arr){
           nodes.add(new Node(value));
        }

        while(nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);
            System.out.println("nodes= " + nodes);

            // 取出 根节点权值 最小的 两颗 二叉树
            Node leftNode = nodes.get(0);
            // 取出 权值第二小的 节点
            Node rightNode = nodes.get(1);

            // 构成一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从ArrayList 中删除处理过的 二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将 parent 加入到 nodea
            nodes.add(parent);

            Collections.sort(nodes);
            System.out.println("处理后的nodes= " + nodes);
        }

        // 返回 huffman 树的 root 节点
        return nodes.get(0) ;
    }
}


class Node implements Comparable<Node>{
    int value ;  // 节点权值
    Node left;   // 左子节点
    Node right ; // 右子节点

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int val){ this.value = val; }

    @Override
    public String toString() { return "Node{" + "value=" + value + '}'; }

    @Override
    public int compareTo(Node k) {
        return this.value - k.value;
    }


}
