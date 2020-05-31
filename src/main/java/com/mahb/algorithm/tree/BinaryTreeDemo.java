package com.mahb.algorithm.tree;

import com.mahb.algorithm.HashTab.HashTableDemo;

import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-04 09:40
 **/
public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

         System.out.println("前序遍历.....");
         binaryTree.preOrder();  //

        // System.out.println("中序遍历.....");
         // binaryTree.infixOrder();  //

        // System.out.println("后序遍历.....");
        // binaryTree.postOrder();  //

        /*System.out.println("后序遍历查找方式.....");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if(resNode != null){
            System.out.println("找到了...." + resNode.getName() +"...."+ resNode.getNo());
        }else {
            System.out.println("没有找到...");
        }*/

        // 测试 删除节点
        binaryTree.delNode(3);
        System.out.println("删除后前序遍历.....");
        binaryTree.preOrder();  //

    }
}

class BinaryTree {
    private HeroNode root;
    public void setRoot(HeroNode root) { this.root = root; }

    // 删除
    public void delNode(int no){
        if(root != null){
            // 判断 root是否为删除的节点
            if(root.getNo() == no){
                root = null;
            }else{
                // 递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，无法删除！！");
        }
    }

    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历！");
        }
    }

    // 前序遍历
    public HeroNode preOrderSearch(int no){
        if(root != null){ return root.preOrderSearch(no) ;
        }else { return null; }
    }

    // 中序遍历
    public HeroNode infixOrderSearch(int no){
        if (root != null){ return root.infixOrderSearch(no);
        }else{ return null; }
    }

    // 后序遍历
    public HeroNode postOrderSearch(int no){
        if(root != null){ return root.postOrderSearch(no) ;
        }else{ return null; }
    }


}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public HeroNode getLeft() { return left; }
    public void setLeft(HeroNode left) { this.left = left; }
    public HeroNode getRight() { return right; }
    public void setRight(HeroNode right) { this.right = right; }

    // 删除
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }
    }

    // 前序遍历 输出
    public void preOrder(){
        System.out.println(this); // 先输出父节点
        // 递归向左子树 前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        // 递归向右子树 前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }
    // 中序遍历 输出
    public void infixOrder(){
        // 递归向左子树 中序遍历
        if (this.left !=null){
            this.left.infixOrder();
        }
        // 输出父 节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    // 后序遍历 输出
    public void postOrder(){
        if(this.left != null){ this.left.postOrder(); }
        if(this.right != null ){ this.right.postOrder(); }
        System.out.println(this); // 最后输出父节点
    }

    // 前序遍历 查找
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历....preOrderSearch.....");
        // 比较当前节点是不是
        if(this.no == no){ return this; }

        // 则判断 当前节点的左子节点是否为空，如果不为空，则递归前序查找
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        // 继续向右 递归前序查找
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    // 中序遍历 查找
    public HeroNode infixOrderSearch(int no){

        // 判断左子节点是否为空，不为空， 进行中序查找
        HeroNode resNode= null;
        if(this.left != null){ resNode = this.left.infixOrderSearch(no); }
        if(resNode != null){ return resNode;}

        System.out.println("进入中序遍历....infixOrderSearch.....");
        // 判断当前节点
        if(this.no == no){ return this;}

        if(this.right != null){ resNode = this.right.infixOrderSearch(no); }
        return resNode;
    }

    // 后序遍历 查找
    public HeroNode postOrderSearch(int no){

        HeroNode resNode = null;
        if(this.left != null){resNode = this.left.postOrderSearch(no) ;}
        if(resNode != null){ return  resNode; }

        if(this.right != null){ resNode = this.right.postOrderSearch(no); }
        if(resNode != null){ return resNode;}

        System.out.println("进入后序遍历....postOrderSearch.....");
        if(this.no== no){ return  this;}
        return resNode;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
