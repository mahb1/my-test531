package com.mahb.algorithm.Huffman;

import sun.misc.IOUtils;

import java.io.*;
import java.util.*;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-04 20:36
 **/
public class HuffmanCode {

    public static void main(String[] args) {

        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);
        List< Node>  nodes =  getNodes(contentBytes);
        System.out.println(nodes);

         Node root = createHuffmanTreeList(nodes);
        System.out.println("前序遍历。。。");
        // preOrder(root);
        // getCodes(root);

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);

       //  System.out.println(" huffmanCodeBytes= " + Arrays.toString(huffmanCodeBytes));

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);

        System.out.println("原字符串为 :" +  new String(sourceBytes));

        // String srcFile = "G://project_read_file/timg.jpg";
        //String dicFile = "G://project_read_file/chu_tian.bmp";

         // zipFile(srcFile,dicFile);
        //unZip(dicFile, "G://project_read_file/chu_tian1.jpg");
        //System.out.println(" 文件成功....");



    }

    // 对文件进行压缩
    public static void zipFile(String srcFile, String dstFile){
        FileInputStream is = null;
        OutputStream os = null ;
        ObjectOutputStream oos = null;

        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()]; // 创建和源文件大小一样的 byte[]
            // 读取 文件
            is.read(b);

            byte[] huffmanBytes = huffmanZip(b);  //  写入了 huffmanCodes
            os = new FileOutputStream(dstFile) ;
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);  // 赫夫曼编码后的字节数组写入压缩 文件

            //  对象流方式写入， 以后恢复源文件时使用
            oos.writeObject(huffmanCodes);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is != null){ is.close(); }
                if(os != null){ os.close(); }
                if(oos != null){ oos.close(); }
            }catch (Exception e){ e.printStackTrace(); }
        }

    }

    // 对压缩 文件进行解压
    public static void unZip(String zipFile, String dstFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[])ois.readObject();

            Map<Byte,String> codes = (Map<Byte,String>)ois.readObject(); // 读取赫夫曼编码表
            byte[] bytes = decode(codes, huffmanBytes); // 解码
            os = new FileOutputStream(dstFile); // 写入到目标文件
            os.write(bytes);

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(os!=null){os.close();}
                if(ois!=null){ois.close();}
                if(is!=null){is.close();}
            }catch (Exception e){ e.printStackTrace(); }
        }
    }

    // 将 一个byte 转成 二进制的字符串, 注意是按 补码进行的；
    private static String byteToBitString(boolean flag , byte b){
        int temp = b;
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp) ;

        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }

    // 进行解码操作
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i < huffmanBytes.length; i++){
            boolean flag = (i == huffmanBytes.length -1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        System.out.println("赫夫曼字节数组对应的二进制字符串 = " + stringBuilder.toString());

        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey()) ;
        }
        System.out.println("map..==..." + map.toString());

        List<Byte> list = new ArrayList<>();

        for(int i=0; i< stringBuilder.length(); ){

            int count = 1;      // 计数器
            boolean flag = true;
            Byte b = null;

            while (flag){
                //
                String key = stringBuilder.substring(i, i+ count); // 进行匹配字符
                b = map.get(key);
                if(b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        System.out.println("------------------------------------");
        System.out.println(Arrays.toString(list.toArray()));

        // list 存放所有字符后
        byte[] b = new byte[list.size()];
        for(int i=0 ; i< list.size(); i++){
            b[i] = list.get(i) ;
        }

        return b;
    }

    // 进行封装
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        Node  huffmanTreeRoot = createHuffmanTreeList(nodes);   // 创建 赫夫曼树
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot); // 生成 赫夫曼编码
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes); // 压缩到 赫夫曼编码字节数组
        return huffmanCodeBytes;
    }

    // 进行压缩 字节
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        System.out.println("测试 stringBuilder =" + stringBuilder.toString());

        // 转成 byte 数组
        // 也可以一句话表示：  len = (stringBuilder.length() + 7 ) / 8;
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建存储压缩后的 byte 数组
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0; // 记录是第几个byte

        for(int i=0; i< stringBuilder.length(); i += 8){  // 因为每 8 位对应一个 byte， 所以步长 + 8；
            String strBytes ;
            if(i + 8 > stringBuilder.length()){ // 不够 8 位
                strBytes = stringBuilder.substring(i);
            }else {
                strBytes = stringBuilder.substring(i, i+8);
            }

            huffmanCodesBytes[index] = (byte) Integer.parseInt(strBytes, 2);
            index ++;
        }

        return huffmanCodesBytes;
    }

    // 将 赫夫曼编码存放在 map ；
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 需要拼接路径，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder() ;

    // 获取 赫夫曼树
    private static Map<Byte, String> getCodes( Node root){
        if(root == null){
            return null;
        }
        // 处理 root 左子树
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    //  赫夫曼树 处理 获取字节
    private static void getCodes( Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){
            // 判断当前node 是叶子节点还是 非 叶子节点
            if(node.data == null){  // 非叶子节点
                // 递归处理 ， 向左
                getCodes(node.left, "0", stringBuilder2);
                // 递归处理 ， 向右
                getCodes(node.right, "1", stringBuilder2);
            } else {  // 说明叶子节点
                // 表示某个叶子节点的最后
                huffmanCodes.put(node.data , stringBuilder2.toString()) ;
            }
        }
    }

    // 前序遍历
    private static void preOrder( Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("为空.....");
        }
    }

    // 获取节点
    private static List< Node> getNodes(byte[] bytes){
        //
        List< Node> nodes = new ArrayList<>();
        // 存储每个 byte 出现的次数 到 map
        Map<Byte , Integer> counts = new HashMap<>();
        for(byte b : bytes){
            if(!counts.containsKey(b)){
                counts.put(b, 1) ;
            }else{
                counts.put(b, counts.get(b)+1) ;
            }
        }

        // 把每个键值对 转成一个Node， 并加入到 Nodes 集合
        for(Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new  Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 通过list 构建赫夫曼树
    private static  Node createHuffmanTreeList(List< Node> nodes){
        while(nodes.size() > 1){
            Collections.sort(nodes);
             Node leftNode = nodes.get(0);
             Node rightNode =nodes.get(1);
            // 创建一颗新的 二叉树，它的根节点 没有data，只有权值
             Node parent = new  Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode) ;

            nodes.add(parent);
        }

        return nodes.get(0);
    }


}

// 节点类
class Node implements Comparable< Node>{
    Byte data ; // 存放数据本身， Ascii
    int weight; // 权值
     Node left;
     Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override  // 从小到大
    public int compareTo( Node o) { return this.weight - o.weight ; }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", weight=" + weight + '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }

    }



}
