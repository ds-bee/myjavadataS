package com.huang.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6 ,9, 5, 8 , 10,11};
        heapSort(arr);

    }

    public static void heapSort(int arr[]){
        int tem = 0;
        //这里就是从最后一个非叶子节点开始，开始进行交换，这里是将数组变为大堆排序
        //注意这里的条件是i--，所以才能实现向上排序
        //如果要改为小堆应该要调整adjustheap的逻辑
        //然后下面的小堆排序改为大堆
        for (int i = arr.length / 2 -1; i>= 0; i--){
            adjustHeap(arr, i, arr.length);
        }

        System.out.println(Arrays.toString(arr));
        //每次数组的长度减一，因为最后一个数已经排好位置了
        for (int j = arr.length-1; j>0; j--){
            //每次排序都是交换栈顶和最后一个元素的值，然后减小的一个排好序了的值
            tem = arr[j];
            arr[j] = arr[0];
            arr[0] = tem;
            //每次排序都是从栈顶开始排
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));

    }

    public static void adjustHeap(int arr[], int i, int lengtht){

        int temp = arr[i];
        //这里是将传入的父节点取子节点,并且能够向下进行比较
        for (int k = i * 2 + 1; k<lengtht; k = k * 2 +1){
            //这里是判断子节点的左右节点，并且条件是左子节点小于右子节点
            if(k+1 < lengtht && arr[k] < arr[k+1]){
                k++;
            }
            //交换值
            if(arr[k] > temp){
                arr[i] = arr[k];
                //如果子节点和父节点交换，就会从子节点继续遍历，并且是一个子树的排序完成
                i = k;
            }else {
                break;
            }
        }
        //因为i有可能会变化，有可能等于k或者原来的值，所以要重新赋值，这里是确定位置的
        arr[i] = temp;
    }

}
