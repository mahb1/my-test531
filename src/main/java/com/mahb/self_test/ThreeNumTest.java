package com.mahb.self_test;

import org.omg.CORBA.portable.IDLEntity;
import sun.jvm.hotspot.tools.StackTrace;

import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-04-04 20:56
 **/
public class ThreeNumTest {


    public static void main(String[] args) {

       StringBuffer s1 = new StringBuffer("aaaa");
       StringBuffer s2 = new StringBuffer("bbbb");
       change(s1, s2);
       System.out.println(s1);
       System.out.println(s2);

    }
    static void change(StringBuffer x, StringBuffer y){
        System.out.println("start method...");
        y = x;
        x = new StringBuffer("cccc");
        System.out.println(y);

        x.append("eeee");
        y.append("ffff");

        System.out.println(x);
        System.out.println(y);

        System.out.println("end method...");


    }





    static List<int[]> list = new ArrayList<>();
    public static void reckon(int[] arr,int num){
        for (int i = 0;i < arr.length;i++){
            int value = num - arr[i];
            Map<Integer,Integer> map1 = new HashMap<>();
            for (int j = i + 1; j < arr.length;j++){
                int[] arr2 = new int[3];
                if (map1.containsKey(arr[j])){
                    arr2[1] = map1.get(arr[j]);
                    arr2[0] = arr[i];
                    arr2[2] = arr[j];
                    list.add(arr2);
                }
                else {
                    map1.put(value - arr[j],arr[j]);
                }
            }

        }
    }



    static List<int[]> listT = new ArrayList<>();
    public static void reckonTwo(int[] arr,int num){
        

    }







}

