package com.mahb.self_test;

import sun.applet.Main;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-04-14 08:05
 **/
public class DeadLock {



/*    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        // byte[] b = new byte[50 * 1024 * 1024];
        List<String> ls = getStrings();
        ls.stream().forEach((s) -> System.out.println("--"+ s ));

        // new Thread(new TheadLock(lockA, lockB) , "Tread_AAA").start();
        // new Thread(new TheadLock(lockB, lockA) , "Tread_BBB").start();
    }*/



    @CallerSensitive
    public static List<String> getStrings() {

        Class cl = Reflection.getCallerClass();
        System.out.println("=="+ cl.getName());

        String str="aabbcccc";
        String[] strs = str.split("");
        return Arrays.asList(strs);
    }


}

class TheadLock implements Runnable{


    private String lockA;
    private String lockB;

    public TheadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (lockA){

            System.out.println(Thread.currentThread().getName() + "\t 自己持有"+lockA+" 尝试获得 " +lockB);

            try { Thread.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有"+lockB+" 尝试获得 " +lockA);

            }
        }

    }
}

