package com.mahb.self_test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-01-18 13:46
 **/
public class CodeBlockDemo <T> {


    public static void main(String[] args)
    {
         Map<Integer, Integer> map = new HashMap<>();
         Integer b = 2;
         Integer i = map.get(2);
         if(i != 0){
             System.out.println(""+ i);
         }
    }

  /*  public T show(int is){

        T isa ;
    }*/

}

