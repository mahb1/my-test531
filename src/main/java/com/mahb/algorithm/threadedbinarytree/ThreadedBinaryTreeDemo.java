package com.mahb.algorithm.threadedbinarytree;

// import org.springframework.web.method.HandlerMethod;

/**
 * @program: algorithm
 * @description:  前序和后续 线索化 自己实现；；；
 * @author: Mr.Mahongbin
 * @create: 2019-10-04 14:41
 **/
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        HeroNode root = new HeroNode(1,"tom");
        HeroNode root2 = new HeroNode(3,"jack");
        HeroNode root3 = new HeroNode(6,"smith");
        HeroNode root4 = new HeroNode(8,"mary");
        HeroNode root5 = new HeroNode(10,"king");
        HeroNode root6 = new HeroNode(14,"dim");

        root.setLeft(root2);
        root.setRight(root3);
        root2.setLeft(root4);
        root2.setRight(root5);
        root3.setLeft(root6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes();

        // 测试
        HeroNode leftNode = root5.getLeft();
        HeroNode rightNode = root5.getRight();
        System.out.println("10号节点的 前驱节点： "+ leftNode);
        System.out.println("10号节点的 后驱节点： "+ rightNode);

        // 线索化 二叉树后，不能在使用原来的遍历方法
        System.out.println(" 使用线索化的方式 遍历线索化 二叉树");
        tree.threadedList();


    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    private int leftType;  // 如果为0，表示指向左子树，为1 表示指向前驱节点
    private int rightType; // 如果为0，表示指向右子树，为1 表示指向后继节点

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public HeroNode getLeft() { return left; }
    public void setLeft( HeroNode left) { this.left = left; }
    public  HeroNode getRight() { return right; }
    public void setRight( HeroNode right) { this.right = right; }

    public int getLeftType() { return leftType; }
    public void setLeftType(int leftType) { this.leftType = leftType; }
    public int getRightType() { return rightType; }
    public void setRightType(int rightType) { this.rightType = rightType; }


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
    public  HeroNode preOrderSearch(int no){
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
    public  HeroNode infixOrderSearch(int no){

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
    public  HeroNode postOrderSearch(int no){

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

// 实现线索化功能的 二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    // 创建要给 指向当前节点的 前驱节点的指针, 递归进行线索化时， pre 总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) { this.root = root; }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    // 遍历 线索化 二叉树的方法
    public void threadedList(){
        HeroNode node = root;
        while (node != null) {

            // 循环找到lefttype ==1 的结点，第一个找到 就是 8节点，后面随着 遍历而变化， 因为当 lefttype = 1 时，
            // 说明 该结点 是 按照线索化， 处理后的 有效节点；
            while (node.getLeftType() ==0 ){
                node = node.getLeft();
            }
            // 打印当前整个节点
            System.out.println(node);

            // 如果当前节点的右指针 指向的是 后继节点，就一直输出
            while(node.getRightType() == 1){
                // 获取到 当前节点的 后继节点
                node = node.getRight();
                System.out.println(node);
            }

            // 替换 这个遍历的节点
            node = node.getRight();
        }
    }

    // 对二叉树 进行中序线索化， node 线索化的节点
    public void threadedNodes(HeroNode node){
        // 为 null，不能进行线索化
        if(node == null){ return;}

        // 先线索化左子树
        threadedNodes(node.getLeft());
        // 线索化当前节点
        if(node.getLeft() == null){   // 左子节点为null
            // 让当前节点左指针 指向前驱 节点
            node.setLeft(pre);
            // 修改当前节点的左指针的类型, 指向前驱节点
            node.setLeftType(1);
        }

        // 处理后继节点
        if(pre!= null && pre.getRight() == null){
            // 让前驱节点的 右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1); // 前驱节点的右指针类型
        }

        pre = node; // 每处理一个节点后，让当前节点是下一个节点的前驱节点

        // 线索化右子树
        threadedNodes(node.getRight());

    }

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