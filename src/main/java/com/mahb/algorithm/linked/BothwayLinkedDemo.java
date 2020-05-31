package com.mahb.algorithm.linked;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-01 10:22
 **/
public class BothwayLinkedDemo {



    public static void main(String[] args) {

        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        BothwayLinke linke = new BothwayLinke();
        linke.add(hero1);
        linke.add(hero2);
        linke.add(hero3);
        linke.add(hero4);

        linke.list();
    }

}
class BothwayLinke{

    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() { return head; }

    public void list(){
        if(head.next == null){ System.out.println("链表是空的。。。"); return ;}
        HeroNode2 temp = head.next;
        while(true){
            if(temp == null ){break;}
            System.out.println(temp);
            temp = temp.next; // 后移， 一定要后移
        }
    }

    // 添加
    public void add(HeroNode2 heroNode2){
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){break;}
            temp = temp.next; // 后移
        }

        // 构成双向 链表
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    // 修改节点的内容
    public void update (HeroNode2 heroNode2){
        if(head.next == null){System.out.println("链表为空..."); return;}
        HeroNode2 temp = head.next;
        boolean flag = false;

        while(true){
            if(temp == null ){ break;}
            if(temp.no == heroNode2.no){ flag = true; break; }
            temp = temp.next;
        }
        if(flag){
            temp.name = heroNode2.name;
            temp.nickname = heroNode2.nickname;
        }else{
            System.out.println("没有找到该节点；；");
        }

    }

    // 删除，对于双向链表，直接找到要删除的节点，自我删除
    public void deleteNode(int no){

        if(head.next == null){System.out.println("链表为空..无法删除.."); return;}
        HeroNode2 temp = head.next; // 辅助变量
        boolean flag = false;  // 是否找到
        while(true){
            if(temp == null){break;}
            if(temp.no == no){flag = true; break;}
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next; // 当前删除节点的 前一个节点的pre  指向 当前节点的下一个节点
            // 如果删除的是最后一个节点 , 则最后一个节点next指向null， 不需要执行下面，否则出现空指针
            if(temp.next != null){ temp.next.pre = temp.pre; }

        }else{

        }
    }

}


class HeroNode2{

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 pre;   // 指向前一个节点
    public HeroNode2 next;  // 指向下一个节点

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
