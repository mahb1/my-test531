package com.mahb.algorithm.stack;

import java.util.Stack;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-01 10:09
 **/
public class TestStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
       while(stack.size()>0){
           System.out.println(stack.pop());
       }
    }
}
