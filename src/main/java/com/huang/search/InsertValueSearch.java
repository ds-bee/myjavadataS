package com.huang.search;

import java.util.Arrays;

public class InsertValueSearch {

    public static void main(String[] args) {
//        int[] arr = new int[100];

//        for (int i = 0; i < 100; i++) {
//            arr[i] = i + 1;
//        }
//        System.out.println(Arrays.toString(arr));



    }

    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal){

        System.out.println("输出了多少次");
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }
        //插值自适应算法
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){
            return insertValueSearch(arr,mid + 1,right, findVal);
        }else if(findVal < midVal){
            return insertValueSearch(arr, left, mid -1,findVal);
        }else {
            return mid;
        }

    }


}
