package com.mahb.algorithm.avl;

import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-05 14:56
 **/
public class AvlTreeDemo {

    public static void main(String[] args) {
       // int[] arr = {4,3,6,5,7,8};
        int[] arr = {10,12,8,9,7,6};

        AVLTree avlTree = new AVLTree();
        for(int i=0 ; i< arr.length ; i++){
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println(" 树的高度："+avlTree.getRoot().height());
        System.out.println(" 左树的高度："+avlTree.getRoot().leftHeight());
        System.out.println(" 右树的高度："+avlTree.getRoot().rightHeight());
        System.out.println(" 根：" + avlTree.getRoot().value);
        System.out.println(" 根 左子节点：" + avlTree.getRoot().left.left.value);

    }
}

class AVLTree{

    private Node root;
    public Node getRoot() { return root; }

    // 添加节点
    public void add(Node node){
        if(root == null) {root = node; }  // 判断 根节点
        else{root.add(node);}
    }

    // 中序遍历
    public void infixOrder() {
        if(root != null){ root.infixOrder();
        }else { System.out.println(" 二叉排序树 是空的 ...."); }
    }

    // 查找要删除的 节点
    public Node search(int value){
        if(root == null ){ return null ;}
        else {return root.search(value) ; }
    }

    // 查找 父节点
    public Node searchParent(int value){
        if(root == null ){ return null; }
        else {return  root.searchParent(value) ; }
    }

    // 传入的节点 当作二叉排序树的根节点； 返回以 node 为根节点 二叉排序树的 最小节点的 值
    public int delRightTreeNode(Node node){
        Node target = node;
        while(target.left != null){
            target = target.left;
        }
        // 这时，target 指向了最小节点
        delNode(target.value); // 删除 最小节点
        return target.value;

    }

    public void delNode(int value){
        if(root == null){return; }
        else{
            Node targetNode = search(value);  // 获取要删除的节点
            if(targetNode == null){ return; } // 没有找到要删除的节点

            // 如果 当前这颗 二叉排序树 只有一个节点, 根节点 root 没有左右子节点
            if(root.left == null &&  root.right == null){ root = null; return; }

            // 查找 targetNode 父节点
            Node parent = searchParent(value);
            // 要删除的节点 是叶子节点
            if(targetNode.left == null && targetNode.right == null){
                // 判断 targetNode 是父节点的左子节点， 还是 右子节点
                if(parent != null ) {
                    if (parent.left != null && parent.left.value == value) {    // 左子节点
                        parent.left = null;
                    } else if (parent.right != null && parent.right.value == value) {  // 右子节点
                        parent.right = null;
                    }
                }else{
                    root = targetNode.left;
                }
            }else if(targetNode.left != null && targetNode.right != null){   // 删除 有两棵 子树的节点
                int minValue = delRightTreeNode(targetNode.right);
                targetNode.value = minValue;

            }else{  // 删除只有一颗树的节点

                if(targetNode.left != null ){   // 如果删除 的节点只有 左子节点
                    if(parent.left.value == value){     // 如果targetNode 是 parent 的左子节点
                        parent.left = targetNode.left;

                    } else{  // targetNode 是 parent 的 右子节点
                        parent.right = targetNode.left;
                    }
                }else{  // 如果删除 的节点只有 右子节点
                    if(parent != null) {
                        if (parent.left.value == value) {     // targetNode 是parent 的左子节点
                            parent.left = targetNode.right;
                        } else {          // targetNode 是 parent 的 右子节点
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}



// 创建 Node 节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) { this.value = value; }

    public int leftHeight(){
        if(left == null ){ return  0; }
        return left.height();
    }

    public int rightHeight(){
        if(right == null ){ return  0; }
        return right.height();
    }

    // 返回  以该节点为 根节点的 高度
    public int height(){
        return Math.max(
                    left == null ? 0: left.height(),
                    right ==null ? 0: right.height()
                )+1 ;
    }

    // 左旋方法
    private void leftRotate(){
        // 新节点 以当前根节点的值返回
        Node newNode = new Node(value);

        // 把新节点的 左子树设置成 当前 节点的左子树
        newNode.left = left;
        // 把新节点的 右子树设置成 当前 节点的 右子树的 左子树
        newNode.right = right.left;

        // 把当前节点的 值 替换成右子节点的 值
        value = right.value ;

        // 把当前节点的 右子树设置成 当前节点右子树 的  右子树
        right = right.right;

        // 把当前节点的 左子树 (左子节点) 设置成新的节点
        left = newNode;
    }

    // 右旋
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    // 添加节点, 递归节点
    public void add(Node node){
        if(node == null){return; }
        // 判断传入节点的值 和当前 子树根节点的值的关系
        if(node.value < this.value){
            if(this.left == null){ // 当前左子节点为空
                this.left = node;
            }else{
                this.left.add(node) ; // 递归向左子树添加
            }
        }else {  // 添加的节点的值 大于当前节点的值
            if(this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        // 当添加完一个节点后，如果：右子树的高度 - 左子树的高度 > 1, 进行 左旋
        if(rightHeight() - leftHeight()  > 1){

            // 如果它的 右子树的 左子树 的高度 大于 它的右子树的 右子树高度
            if(right != null && right.leftHeight() > right.rightHeight()){
                // 先对 右子 节点进行  右旋转， 然后对当前节点进行 左旋转
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate(); // 直接进行左 旋转
            }
            return; // 此处必须返回，当满足 此 if 时，说明已经处理了平衡，不需要进行下边的 处理；
        }

        // 当添加完一个节点后，如果：左子树的高度 - 右子树的高度 > 1, 进行 右旋
        if(leftHeight() - rightHeight() > 1){

            // 如果它的左子树的 右子树高度 大于 它的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                // 先 对当前节点的 左节点(子树) -- > 左旋转
                left.leftRotate();
                // 再 对当前节点 进行右旋转
                rightRotate();
            }else {
                rightRotate();  // 直接进行 右旋转即可
            }

        }
    }

    // 中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    // 查找 要删除的节点
    public Node search(int value){
        if(value == this.value){return  this;}
        else if( value < this.value){ // 小于当前节点，向左子树递归查找
            if(this.left == null){ return null; }
            return this.left.search(value);
        }else{    // 向右查找
            if(this.right== null){ return null; }
            return this.right.search(value);
        }
    }

    // 查找 父节点
    public Node searchParent(int value){
        if( (this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        }else{
            // 如果查找的值小于当前 节点的值，并且当前节点的左子节点不为空
            if(value < this.value && this.left != null){
                return this.left.searchParent(value) ; // 左子树递归查找
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value) ; // 右子树递归查找
            } else{
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }


}

