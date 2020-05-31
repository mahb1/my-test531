package com.mahb.self_test;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-05-28 22:23
 **/
public class A {


    public static void main(String[] args) {

        String[] strs = {"_A", "_B", "_C"};
        Thread pre = Thread.currentThread();

        for(int i=0; i<3; i++){
            Thread thread = new Thread(new Demio(strs[i], pre));
            thread.start();
            pre = thread;
        }
        System.out.println("begin");
    }

    static class Demio implements Runnable{

        private String str;
        private Thread thread;
        public Demio(String str, Thread thread){ this.str = str;this.thread = thread; }

        @Override
        public void run() {

            String string = this.str;
            try {
                this.thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(str);
        }
    }

}


