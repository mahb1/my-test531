package com.mahb.algorithm.binarysorttree;

/**
 * @program: algorithm
 * @description:  找到左子树最大的 节点值；
 * @author: Mr.Mahongbin
 * @create: 2019-10-05 10:25
 **/
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int arr[] = {7,3,10,12,5,1,9, 0};
        BinarySortTree bst = new BinarySortTree();
        for(int i= 0; i< arr.length; i++){
            bst.add(new Node(arr[i]));
        }

        System.out.println("中序遍历后");
        bst.infixOrder();

        bst.delNode(10);
        System.out.println("删除节点后");
        bst.infixOrder();

    }
}

// 二叉树
class BinarySortTree{

    private Node root;
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
