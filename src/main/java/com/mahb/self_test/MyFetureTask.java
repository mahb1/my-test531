package com.mahb.self_test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-05-12 18:34
 **/
public class MyFetureTask implements Callable<String> {


    @Override
    public String  call() throws Exception {

        List<String> l = new ArrayList<String>();

        for(int i=0 ;i< Integer.MAX_VALUE; i++){

            l.add("aaa");
        }

        return l.toString();
    }


}

