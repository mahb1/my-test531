package com.mahb.algorithm.HashTab;

// import org.springframework.beans.propertyeditors.CurrencyEditor;
// import org.springframework.web.servlet.HandlerAdapter;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-03 21:24
 **/
public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add, 添加雇员");
            System.out.println("list, 显示雇员");
            System.out.println("find, 查找雇员");
            System.out.println("exit, 退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id ：");
                    int id = scanner.nextInt();
                    System.out.println("输入名字：");
                    String name = scanner.next();
                    // 创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "find":
                    System.out.println("请输入查找的ID：");
                    int findid = scanner.nextInt();
                    hashTab.findEmpById(findid);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }

}

class HashTab{
    private EmpLinkedList[] empLinkedListsArray;
    private int size ; // 多少条链表

    public HashTab(int size){
        this.size = size;
        empLinkedListsArray = new EmpLinkedList[size];
        for(int i=0; i< size; i++){
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        int empLinkedListNO = hashFun(emp.id);
        empLinkedListsArray[empLinkedListNO].add(emp);
    }

    // 遍历所有链表
    public void list(){
        for(int i=0; i<size; i++){
            empLinkedListsArray[i].list((i+1));
        }
    }

    // 散列函数， 取模法
    public int hashFun(int id){
        return id % size ;
    }

    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListsArray[empLinkedListNo].findEmpById(id);
        if(emp != null){
            System.out.printf("第 %d 条找到该雇员 id = %d\n " , (empLinkedListNo+1), id );
        }else {
            System.out.println(" 哈希表中， 没有找到该雇员");
        }
    }

}

class Emp{
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int id;
    public String name;
    public Emp next;
}

class EmpLinkedList{
    // 头指针，执行第一个Emp, 因此这个链表的head ，是直接执行第一个Emp
    private Emp head;

    public void add(Emp emp){
        if(head == null){ head = emp;return; }

        Emp cuEmp = head;
        while (true){

            if(cuEmp.next == null){ break; }
            cuEmp = cuEmp.next;  // 后移
        }
        cuEmp.next = emp;
    }

    public void list(int no){
        if(head == null){ System.out.println(" 第 "+no+ " 链表为空.....");return; }

        System.out.println();
        System.out.println("第 "+no+ " 链表信息为：");
        Emp curEmp = head; // 辅助指针

        while (true){
            System.out.printf(" => id=%d ,  name = %s\t", curEmp.id, curEmp.name);
            if(curEmp.next == null){ break; }
            curEmp = curEmp.next; // 后移， 遍历
        }
    }

    // 根据ID 查找雇员
    public Emp findEmpById (int id){

        if(head == null){ System.out.println(" 链表为空....");return null; }
        Emp curEmp = head;

        while (true){
            if(curEmp.id == id){ break; }
            if(curEmp.next == null){ curEmp = null;break; }
            curEmp = curEmp.next;
        }
        return curEmp;

    }
}