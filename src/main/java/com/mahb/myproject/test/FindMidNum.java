package com.mahb.myproject.test;

import org.apache.logging.log4j.util.PropertySource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-05-31 18:40
 **/
public class FindMidNum {

    public static void main(String[] args) {

        Integer[] ints = new Integer[]{1,3,4,7,20,55,3,8,2,3,10,2,3,89,32,22,3,6,4,4,2};

        findMidNumber(ints);
    }


    public static void findMidNumber(Integer[] ints){

        Map<Integer, Integer> map = new HashMap<>();

        IntStream.range(0, ints.length).forEach(i ->
                        map.put(ints[i], map.getOrDefault(ints[i], 0)+1)
                );

        List<Map.Entry<Integer, Integer>> sortMap =  map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        int index = 0;
        for(Map.Entry m : sortMap){
            index ++;
            System.out.println(m.getKey()+"---"+ m.getValue());

            if(index == 3) break;
        }

    }


}

