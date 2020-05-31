package com.mahb.self_test;

/**
 * @program: algorithm
 * @description:
 * @author: Ma hong bin
 * @create: 2020-03-17 22:01
 **/
public class AccumulateAlgorithm {


    public static void main(String[] args) {

        int[] arr = {3,4,8,9,10,12,14,18,19,20,21,22,23,26,27,28,29,30,31,35,36,37,45,46};

        System.out.println(accumulateMethod2(arr));

    }


    public static int accumulateMethod(int[] arr){

        int max = 0;
        int longMax = 0;

        for(int i=0; i< arr.length; i++){

            if(i < arr.length -1&& arr[i]+1 == arr[i+1]){ max ++ ;
            } else{

                if(longMax ==0 && max != 0) { longMax = max; }
                longMax = Math.max(longMax,max);
                max = 1;
            }
        }

        return longMax ;
    }


    public static int accumulateMethod2(int[] arr){

        int max = 0;
        int longMax = 0;
        int readIndex = 0;
        int[] maxArr = new int[]{};

        for(int i=0; i< arr.length; i++){

            if( i < arr.length -1 &&  arr[i]+1 == arr[i+1]){ max ++ ;

            } else{

                // 如果包含并列最大， 只取第一个， 如果取最后一个， 改成 追加 longMax == max
                if((longMax ==0 && max != 0) || longMax < max){
                    longMax = max;
                    readIndex = i - longMax +1;
                    maxArr = new int[longMax];
                    System.arraycopy(arr, readIndex, maxArr, 0, longMax  );
                }
                max = 1;
            }
        }
        for(int i=0; i< maxArr.length ; i++){
            System.out.println(".."+ maxArr[i]);
        }

        return longMax ;

    }

}

