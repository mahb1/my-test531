package com.mahb.self_test;

import sun.applet.Main;

import javax.naming.directory.SearchResult;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-01-13 16:45
 **/
public class SelfTest {

    Integer first  ;
    int secondea  ;
    String stree ;

    public SelfTest(){
        stree = "s";
        System.out.println("B");

    }

    public static void main(String[] args) {

       // System.out.println(strs.replace("ccc",""));
       // System.out.println("----"+ Runtime.getRuntime().availableProcessors());
       // String v = xiaoXiao(strs, strs.length(), 3);


        char[] c = new char[]{};
        
        List<Integer> list = new ArrayList<>();
        list.size();

        SelfTest st = new SelfTest();

       System.out.println( st.first );
        System.out.println(st.secondea );
        System.out.println(st.stree);


    }

   public static String xiaoXiao(String strs, Integer strLen, Integer xiaoLen){

        if(xiaoLen == 1) return "";
        char[] charVS = strs.toCharArray();
        int chIndex = xiaoLen-1;
        int charN = 0;

        for(int i=0 ; i< strLen; i++){

            if( chIndex >= strLen ){ break; }
            if(charVS[i] == charVS[chIndex]){

                charN ++;
                if(charN == xiaoLen){
                    chIndex = i + xiaoLen  ;
                    charN = 0;
                    strLen = strLen - xiaoLen;

                    String reV = strs.substring(i-xiaoLen+1 , i+1);
                    strs = strs.replace(reV,"");
                    charVS = strs.toCharArray();

                    i = i - xiaoLen -2;
                }
            } else {
                chIndex = i + xiaoLen  ;
            }
        }
        return strs;
    }

}

