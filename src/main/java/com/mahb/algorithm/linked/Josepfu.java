package com.mahb.algorithm.linked;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.ForkJoinPool;

/**
 * @program: algorithm
 * @description: 约瑟夫问题， 小孩出圈
 * @author: Mr.Mahongbin
 * @create: 2019-10-01 12:57
 **/
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(7);
        list.list();

        list.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList {
    // 创建 first 节点，没有编号
    private Boy first =null;

    // 添加小孩节点，构建成一个环形列表
    public void addBoy (int nums){
        if(nums < -1){System.out.println("nums 的值不正确");  return; }

        Boy curBoy = null; // 辅助指针，构建环形链表
        for (int i=1; i<=nums; i++){
            Boy boy = new Boy(i);
            if( i==1 ){
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让 curBoy 指向第一个小孩
            }else{
                curBoy.setNext(boy); // 新加入的boy 进行追加到尾节点
                boy.setNext(first);  // 尾节点的 next指针 指向 first
                curBoy = boy; //  curBoy 等于 boy；
            }
        }
    }

    public void list(){
        if(first == null){ System.out.println(" 链表为空...."); return; }

        // 因为first 不能动，因此使用辅助指针 完成遍历
        Boy curBoy = first;
        while(true){
            System.out.println("编号为 : "+ curBoy.getNo() );
            if(curBoy.getNext() == first){ System.out.println(" 遍历完毕----------------"); break; }
            curBoy = curBoy.getNext();
        }

    }

    // 根据用户输入， 计算小孩出圈的顺序 ; startNo 几个小孩开始数数，countNum 表示数几下，nums 最初多少小孩在圈中
    public void  countBoy(int startNo, int countNum, int nums){
        if(first == null || startNo <1 || startNo > nums){ System.out.println(" 参数输入有误，请确认！"); return; }
        // 创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        while(true){
            if(helper.getNext() == first){break;}
            helper = helper.getNext();
        }
        // 小孩报数前，先让 first 和 helper 移动 K- 1 次；
//        for(int j = 0; j< startNo-1; j++){
//            first = first.getNext();
//            helper = helper.getNext();
//        }
        // 当小孩报数时，让first 和 helper 指针 同时 移动 m-1 次，然后出圈；
        // 循环进行，直到圈中只要一个 节点
        while(true) {
            if(helper == first){break;}
            for(int j=0 ; j< countNum-1 ; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(" 小孩出圈："+ first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈里的小孩编号：" +  helper.getNo());
    }


}


class Boy {
    private int no;
    private Boy next;
     Boy(int no){this.no = no ;}

    public int getNo() { return no; }

    public void setNo(int no) { this.no = no; }

    public Boy getNext() { return next; }

    public void setNext(Boy next) { this.next = next; }


}
