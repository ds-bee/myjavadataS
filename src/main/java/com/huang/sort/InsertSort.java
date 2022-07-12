package com.huang.sort;

public class InsertSort {

    public static void main(String[] args) {

    }

    public static void insertSort(int[] arr){
        //要插入的变量
        int insertVal = 0;
        //要插入的位置
        int insertIndex = 0;
        //从第二个数开始排序
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            //当插入位置大于一 且 插入值小于插入位置的值的时候
            //插入位置是一直小的，每次插入不满足条件就会退出循环
            while (insertIndex >= 0 && insertVal< arr[insertIndex]){
                //将数组往前进行遍历经过一次循环，insertindex不断减小
                //此时插入的变量还在，是在insertval中保存的
                arr[insertIndex + 1] = arr[insertIndex];
//                这个是我写错了的，需要退出循环，才能找到插入的值
//                arr[insertIndex] = insertVal;
                insertIndex--;
            }
            //退出循环因为在while循环中insertindex有自减的操作，要还原真实的位置
            //所以需要进行加一，并键要插入变量放入其中
            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }

        }


    }

}
