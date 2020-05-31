package com.mahb.algorithm.tree;

import java.util.Arrays;

/**
 * @program: algorithm
 * @description:
 * @author: Mr.Mahongbin
 * @create: 2019-10-04 17:30
 **/
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9};
        //heapSort(arr);

        System.out.println(System.currentTimeMillis());
    }

    public static void heapSort(int arr[]){
        System.out.println("堆排序！！");
        int temp = 0 ;

        adjustHeap(arr, 1, arr.length);
        //System.out.println("第一次：" + Arrays.toString(arr));

        adjustHeap(arr, 0 ,arr.length);
        //System.out.println("第二次：" + Arrays.toString(arr));

        // 将无序序列 构建成一个堆，根据升序降序 需求选择 大顶堆 或 小顶堆
        for(int i = arr.length / 2 -1; i >= 0; i--){
            adjustHeap(arr,i,arr.length);
        }

        // 将堆顶元素 与末尾元素交换，将最大元素 "沉" 到数组末端
        // 重新调整结构，使其满足堆定义，然后 继续交换堆顶元素 与当前 末尾元素，反复执行 调整+ 交换步骤，直到整个序列有序；
        for(int j=arr.length -1 ; j>0 ; j--){
            // 交换
            temp = arr[j] ;
            arr[j] = arr[0] ;
            arr[0] = temp;

            adjustHeap(arr,0, j);
        }

        System.out.println("数组 ：" + Arrays.toString(arr));

    }

    // 将一个数组(二叉树)， 调整 成一个 大顶堆 ； arr数组，i 非叶子节点索引，length 对多少个元素进行调整
    // 完成 将以 i 为对应的 非叶子节点的树 调整 成大顶堆；
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i]; // 先取出当前元素的值，保存在临时变量；

        for(int k= i *2 +1; k < length; k = k*2+1 ){
            if( k+1 <length && arr[k] < arr[k+1]){  // 说明左子节点的值小于 右子节点的值
                k++;  // k 指向 右子节点
            }
            if(arr[k] > temp){  // 如果子节点大于 父节点
                arr[i] =  arr[k] ;  // 把较大的值 赋给 当前节点
                i = k; //  i 指向 k ，继续循环比较
            }else{
                break;
            }
        }
        // 当 for 循环结束后，我们已经 将以 i 为父节点的树的最大值，放在最顶(局部)
        arr[i] = temp; // 将 temp 值放到 调整后的位置
    }
}
