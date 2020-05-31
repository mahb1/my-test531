package com.mahb.algorithm.linked;

import java.util.Stack;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-01 07:35
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero4);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero2);
        linkedList.list();

        linkedList.deleteNode(4);
        System.out.println(" 删除后的列表：");
        linkedList.list();

        System.out.println(linkedList.getLength(linkedList.getHead()));

    }
}

class SingleLinkedList{

    private HeroNode head = new HeroNode(0,"","");

    // 普通节点
    public void add(HeroNode node){
        HeroNode temp = head;
        while(true){
            // 查找最后一个节点；最后一个节点的next指向null；
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    // 根据排名添加英雄
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;

        while(true){
            if(temp.next == null ){
                break;
            }
            if(temp.next.no > heroNode.no){ // 编号已找到
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println("准备插入的英雄编号已存在！");
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 遍历
    public void list(){
        if(head.next == null ){
            System.out.println("链表为空。。。");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    // 删除
    public void deleteNode(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.next.no == no){
                flag = true; // 找到删除节点
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("当前删除节点不存在");
        }
    }

    // 返回单链表有效节点的个数
    public int getLength(HeroNode node){

        if(head.next == null) { return  0;}
        int length = 0 ;
        HeroNode cur = head.next ;
        while (cur != null){
            length ++;
            cur = cur.next;
        }
        return length;
    }

    // 查找单链表中的倒数第K个节点；
    public HeroNode findLastIndexNode(HeroNode head, int index){
        if(head.next == null){return  null;}
        int size = getLength(head); // 获得总链表长度
        // 第二次遍历，size - index 位置，就是我们倒数第K 个节点；
        if(index <= 0 || index > size){ return null ; }

        HeroNode cur = head.next;
        for(int i=0; i< size-index ; i++){
            cur = cur.next;
        }
        return cur;
    }

    // 将单链表反转
    public void revertLinked(HeroNode head){
        if(head.next == null || head.next.next ==null){ return; }

        HeroNode cur = head.next; // 辅助指针，帮助遍历原来的链表；
        HeroNode next = null;     // 指向当前节点 【cur】的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        while(cur != null){
            next = cur.next;  // 先暂时 保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next; // 将cur 的下个节点 指向新的 链表的最前段
            reverseHead.next = cur; // 将 cur 连接到新的 链表
            cur = next ; //  让cur 后移
        }

        // 将 head.next  指向 reverseHead.next ， 实现单链表反转；
        head.next= reverseHead.next;

    }

    // 从尾到头打印 单链表；利用栈先进后出的特点，不改变链表的特点结构
    public void reversPrint(HeroNode head) {
        if(head.next == null){return;}
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur !=null) {
            stack.push(cur);
            cur = cur.next; // 后移，压入下一个节点；
        }
        // 出栈 打印
        while(stack.size()>0){
            System.out.println(stack.pop()); // stack的 特点先进后出
        }

    }

    // 合并两个有序的单链表；自己实现；

    public HeroNode getHead() { return head; }
}



class HeroNode{

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}