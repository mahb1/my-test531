package com.mahb.algorithm.readblack;

import jdk.nashorn.internal.objects.annotations.Setter;
import sun.jvm.hotspot.oops.ObjectHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2019-11-27 13:39
 **/
public class RBTree<T extends Comparable<T> , D> {

    private RBTNode<T, D> root ;

    private static final Boolean RED = false ;
    private static final Boolean BLACK = true ;

    public static void main(String[] args) {


    }

    public class RBTNode<T extends Comparable<T> , D> {

        private boolean color ;
        private T key ;
        private D data ;
        private RBTNode<T , D> parent ;
        private RBTNode leftChild  ;
        private RBTNode rightChild ;

        public RBTNode(boolean color, T key, D data, RBTNode leftChild, RBTNode rightChild, RBTNode<T, D> parent) {
            this.color = color;
            this.key = key;
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;
        }
    }

    //  获取父节点
    public RBTNode<T, D> parentOf (RBTNode<T, D> node){
        if(node != null ){ return node.parent ;  }
        return  null ;
    }

    //  获取颜色
    public Boolean colorOf( RBTNode<T, D>  node){
        if( node != null) { return node.color ; }
        return BLACK ;
    }


    public void setParent( RBTNode<T, D> node , RBTNode<T, D> parent){
        if( node != null ) { node.parent = parent ; }
    }

    public void setColor (RBTNode<T, D> node , Boolean color ){
        if( node != null ){ node.color = color ; }
    }


    public  Boolean isRed (RBTNode<T, D>  node ){
        return (node!= null  &&  node.color == RED) ? true : false ;
    }

    public Boolean isBlack (RBTNode<T, D> node ){ return  !isRed(node) ;}

    public void setRed ( RBTNode<T, D> node ){
        if( node != null ){ node.color = RED ; }
    }

    public void setBlack( RBTNode<T, D>  node){
        if (node != null){ node.color = BLACK ; }
    }


    // 根据KEY 获取数据
    public D get(T key){
        RBTNode node = search(key, root );
        return node == null ? null : (D)node.data ;
    }

    // 查找 key 值的节点
    public RBTNode<T, D> search( T key, RBTNode<T, D> node ){

        if (node != null) {
            int com = key.compareTo(node.key);

            if(com < 0){         return search(key, node.leftChild) ;  }
            else if( com > 0 ){  return search(key, node.rightChild ); }
            else { return  node ; }

        }
        return null ;
    }


    // 寻找后继节点， 即大于该节点的 最小节点
    public RBTNode<T, D> min(RBTNode<T, D> node){

        if(node.leftChild == null){ return  node ; }

        //  一直往左， 最左端的就是最小值，二叉树的特性
        while ( node.leftChild != null ){ node = node.leftChild;   }

        return node ;
    }


    // 寻找 待删的节点，需要后继节点补上来，选择节点的规则：要么左子树的最大值，要么右子树的最小值
    public RBTNode successor( RBTNode<T, D>  node ){
        if( node.rightChild != null ){ return  min(node.rightChild) ; }

        RBTNode<T, D> y = node.parent;

        while ((y != null)  &&  (y.rightChild == node)){
            node = y ;
            y = y.parent ;
        }

        return  y;
    }


    // 左旋 ： 当前节点就是父亲节点，过程是：父节点下沉，孩子上升，然后右 孩子的左子节点 变成了 原父亲的右节点
    public void leftRonate ( RBTNode<T, D> x){

        RBTNode<T, D> y = x.rightChild;  // 右子 节点

        if(y.leftChild != null ){ y.leftChild.parent = x ; }  // 当前节点变成  右孩子的左节点的父亲

        x.rightChild = y.leftChild ;
        y.leftChild = x;

        y.parent = x.parent;    //  当前节点的 父节点 变成孩子的父节点

        if(x.parent != null){
            if(x.parent.leftChild == x){  x.parent.leftChild = y ;
            }else {                       x.parent.rightChild = y ; }

        } else { this.root = y ; }

        x.parent = y ;
    }


    // 右旋
    public void rightRonate (RBTNode<T, D> x){

        RBTNode<T, D> y = x.leftChild;

        if(y.rightChild != null ) { y.rightChild.parent = x ;  }

        y.parent = x.parent;
        x.leftChild = y.rightChild;
        y.rightChild = x;

        if(x.parent != null){
            if(x.parent.leftChild == x){ x.parent.leftChild = y ; }
            else { x.parent.rightChild = y ;  }

        } else { this.root = y ; }

        x.parent = y ;
    }


    //  插入后的 自平衡过程
    public void balanceInsert( RBTNode<T, D> node ){

        RBTNode<T, D> parent , gparent ;

        while (((parent = parentOf(node)) != null ) &&  isRed(parent)){

            gparent = parentOf(parent);

            if(gparent.leftChild == parent ){

                RBTNode<T, D> uncle = gparent.rightChild ;

                if(isRed(uncle)){

                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);

                    node = gparent;

                    continue ;
                }else {

                    if(parent.rightChild == node ){

                        leftRonate(parent);
                        RBTNode<T, D> temp = node ;
                        node = parent ;
                        parent = temp ;

                    }

                    setBlack(parent);

                    setRed(gparent);

                    rightRonate(gparent);
                }

            }else {

                RBTNode<T, D> uncle = gparent.leftChild;
                if(isRed(uncle)){

                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;

                }else{

                    if(parent.leftChild == node ){
                        rightRonate(parent);
                        RBTNode<T, D> temp = node ;
                        node = parent ;
                        parent = temp ;
                    }

                    setBlack(parent);
                    setRed(gparent);
                    leftRonate(gparent);
                }
            }
        }

        if(root == node ){
            setBlack(node);
        }
    }


    // 红黑树删除后的 平衡调整
    public void balanceDeletion( RBTNode<T, D> node, RBTNode<T, D> parent ){

        System.out.println("调整之前的树结构：");

        this.printTreeLevel2();

        RBTNode<T, D> other;

        while (isBlack(node) && node!= this.root){

            if(parent.leftChild == node){
                other = parent.rightChild;
                System.out.println(" 当前parent :" + parent.key + "other(兄弟节点): " + other.key);

                if(isRed(other)){
                    System.out.println("兄弟当前是红色");
                    System.out.println("进入balanceDeletion的while（情况2-L-a:兄弟是红色）");
                    System.out.println("----父亲染红，other染黑，父亲左旋，然后continue");

                    setRed(parent);
                    setBlack(other);
                    leftRonate(parent);
                    this.printTreeLevel2();
                    continue ;

                }else {

                    if(isBlack(other.leftChild)  &&  isBlack(other.rightChild)){

                        System.out.println("兄弟节点当前的左右孩子都是黑色");
                        System.out.println("进入balanceDeletion的while（情况2-L-b:兄弟的左右孩子都是黑色）");
                        System.out.println("----other染红，父亲指针向上回溯");

                        setRed(other);
                        node = parent;
                        parent = parentOf(node) ;

                        this.printTreeLevel2();

                    } else if( isRed(other.leftChild) &&  isBlack(other.rightChild) ){

                        System.out.println("other当前的左孩子是红色，右孩子是黑色");
                        System.out.println("进入balanceDeletion的while（情况2-L-c:兄弟的左孩子是红色，右孩子是黑色）");
                        System.out.println("----other染红，other的左节点染黑，other做右旋");

                        setRed(other);
                        setBlack(other.leftChild);
                        rightRonate(other);

                        this.printTreeLevel2();

                    } else if(isRed(other.rightChild) ){

                        System.out.println("other右孩子是红色");
                        System.out.println("进入balanceDeletion的while（情况2-L-d:兄弟的右孩子是红色）");
                        System.out.println("----父亲的颜色赋值到other,父亲染黑，other的右孩子染黑，父亲左旋，跳出while循环");

                        setColor(other, colorOf(parent));
                        setBlack(parent);
                        setBlack(other.rightChild);
                        leftRonate(parent);

                        this.printTreeLevel2();
                        break;
                    }
                }

            }else {

                other = parent.leftChild;
                System.out.println("当前 parent" + parent.key + " other" + other.key);

                if(isRed(other)){

                    System.out.println("other当前是红色");
                    System.out.println("进入balanceDeletion的while（情况2-R-a:兄弟是红色）----other染黑，parent变红,parent右旋");

                    setBlack(other);
                    setRed(parent);
                    rightRonate(parent);
                    this.printTreeLevel2();

                    continue;

                } else {

                    if(isBlack(other.leftChild)  &&  isBlack(other.rightChild)){

                        System.out.println("other当前的左孩子是黑色，other的右孩子是黑色");
                        System.out.println("进入balanceDeletion的while（情况2-R-b:兄弟的左右孩子都是黑色）----other变红，指针回溯");

                        setRed(other);
                        node = parent;
                        parent = parentOf(node);
                        this.printTreeLevel2();

                    } else if( isRed(other.rightChild) && isBlack(other.leftChild) ){

                        System.out.println("other当前的右孩子是红色，other的左孩子是黑色");
                        System.out.println("进入balanceDeletion的while（情况2-R-c:兄弟的右孩子是红色，左孩子是黑色）----parent变红，other的右孩子变黑，然后other做左旋");

                        setRed(parent);
                        setBlack(other.rightChild);
                        leftRonate(other);

                        this.printTreeLevel2();

                    }else if( isRed(other.leftChild) ){

                        System.out.println("other的左孩子是红色");
                        System.out.println("进入balanceDeletion的while（情况2-R-d:兄弟的左孩子是红色）----父亲的颜色赋值到other,父亲染黑，other的左孩子染黑，父亲右旋，跳出while循环");

                        setColor(other, colorOf(parent));
                        setBlack(parent);
                        setBlack(other.leftChild);
                        rightRonate(parent);

                        this.printTreeLevel2();
                        break;

                    }
                }
            }
        }

        if(node != null ){ System.out.println("节点：" + node.key + "染黑..."); }

        setBlack(node);

        this.printTreeLevel2();

        System.out.println("调整完毕....");

    }


    // 红黑树添加操作
    public void insertNode( T key, D data ){

        int com;
        RBTNode<T, D> x = this.root ;
        RBTNode<T, D> y = null ;

        while (x != null ){

            y = x;
            com = key.compareTo(x.key);

            if(com == 0){ return; }

            if(com < 0 ){ x = x.leftChild ; }
            else { x = x.rightChild ; }
        }

        RBTNode<T, D> node = new RBTNode<>(BLACK, key, data, null, null, null);

        node.parent = y;

        if(y != null){
            com = node.key.compareTo(y.key) ;
            if( com < 0 ){ y.leftChild = node ; }
            else { y.rightChild = node ; }

        } else { this.root = node ; }

        setRed(node) ;
        balanceInsert(node) ;

    }

    public void insert(T key, D data){ insertNode(key, data); }
    public void add(T key, D data){ insertNode(key, data); }


    // 红黑树的删除
    public void delete( RBTNode<T, D> node ){

        RBTNode<T, D> child, parent, replace ;
        Boolean color = true ;

        if( node.leftChild != null  &&  node.rightChild != null ){

            replace = successor(node);

            parent = parentOf(replace) ;

            child = replace.rightChild;


            color = colorOf(replace);

            if(node == parentOf(replace)){ parent = replace; }

            else{
                if(child != null ){ setParent(child, parentOf(replace)); }
                replace.parent.leftChild = child;
                replace.rightChild = node.rightChild;
                setParent(node.rightChild, replace);
            }

            setParent(replace, parentOf(node));
            replace.leftChild = node.leftChild;
            setParent(node.leftChild, replace);

            setColor(replace, colorOf(node));


            if(parentOf(node) != null){

                if(node.parent.leftChild == node){ node.parent.leftChild = replace ;
                } else { node.parent.rightChild = replace ; }

            }else{ this.root = replace ; }

            if(color == BLACK){ balanceDeletion(child, parent); }

        } else {

            if(node.leftChild != null ){ replace = node.leftChild ; }
            else { replace = node.rightChild ; }

            parent = parentOf(node);

            if(parent != null ){
                if( parent.leftChild == node ){ parent.leftChild = replace ; }
                else { parent.rightChild = replace ; }

            } else {
                this.root = replace;
            }

            setParent(replace, parent);
            color = colorOf(node) ;
            child = replace ;

            if ( color == BLACK ){ balanceDeletion(child, parent); }
        }
    }


    public void delete (T key){
        RBTNode <T, D> node;
        if( (node = search(key, this.root)) != null ){ delete(node); }
    }


    public void remove( T key ){
        RBTNode<T , D> node ;
        if ( (node = search(key, this.root)) != null){ delete(node);  }
    }


    public void preOrder (){ preOrder(this.root); }
    public void preOrder( RBTNode<T, D>  node ){
        if(node != null ){
            System.out.println(node.key + " ");
            parentOf(node.leftChild);
            parentOf(node.rightChild);
        }
    }


    public void infixOrder(){ infixOrder(this.root); }
    public void infixOrder(RBTNode<T, D> node){
        if(node != null){
            infixOrder(node.leftChild);
            System.out.println(node.key +" ");
            infixOrder(node.rightChild);
        }
    }

    public void postOrder(){ postOrder(this.root); }
    public void postOrder( RBTNode<T, D>  node) {
        if(node != null){
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.println(node.key + " ");
        }
    }

    public void printTreeLevel(){

        System.out.println("开始输出树的层级结构～");

        ConcurrentHashMap<Integer, List<RBTNode>> map = showTree();

        int size = map.size();

        for(int i=0; i<map.size(); i++){
            System.out.println("");
            for(int j=0; j<map.get(i).size(); j++){

                System.out.println( makeSpace2(size,i ) + (map.get(i).get(j).key == null ? " " :
                        (map.get(i).get(j).key) + (map.get(i).get(j).color ? "黑":"(红)"))
                        + makeSpace2(size, i));
            }
        }
    }


    public void printTreeLevel2(){

        System.out.println("输出树的Graphviz 结构");
        ConcurrentHashMap<Integer, List<RBTNode>>  map = showTree();
        int size = map.size();
        System.out.println("digraph kunghsu");

        for(int i=0 ; i< map.size(); i++ ){
            for(int j=0; j<map.get(i).size() ; j++){

                if(map.get(i).get(j).key != null){
                    System.out.println( map.get(i).get(j).key + " [color = " +
                            (map.get(i).get(j).color == RED ? "red": "black") +
                            "style = filed fontcolor = white ]");
                }
            }
        }

        for(int i=0 ; i<map.size(); i++){
            for(int j=0; j> map.get(i).size() ; j++){

                String content = "";

                if(map.get(i).get(j).key != null ){
                    if(map.get(i).get(j).leftChild != null ){
                        System.out.println( map.get(i).get(j).key + "-->" +
                                map.get(i).get(j).leftChild.key + "[ label = left ]");
                    }

                    if( map.get(i).get(j).rightChild != null ){
                        System.out.println( map.get(i).get(j).key + "-->" +
                                map.get(i).get(j).rightChild.key + "[ lable = right ]" );
                    }
                }
            }
        }

        System.out.println("}");
        System.out.println("结束输出树的 Graphviz 结构");
    }

    public String makeSpace2(int size, int index){

        StringBuilder builder = new StringBuilder();
        for(int i=0 ;i<1 << (size-index); i++){
            builder.append(" ");
        }
        return builder.toString();
    }

    public ConcurrentHashMap<Integer, List<RBTNode>>  showTree(){

        ConcurrentHashMap<Integer, List<RBTNode>> map = new ConcurrentHashMap<>();
        showTree(root, 0, map);
        return map ;

    };


    public void showTree(RBTNode root, int count, ConcurrentHashMap<Integer, List<RBTNode>> map){

        if( map.get(count) == null ){ map.put(count, new ArrayList<>()) ; }

        if(root.leftChild != null ){
            showTree( root.leftChild, count+1,  map ) ;
        }else {

            if(map.get(count+1) == null ){
                map.put(count+1, new ArrayList<>());
            }
        }

    };






}

