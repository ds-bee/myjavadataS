package com.huang.sort;

import java.util.Arrays;

public class bublleSort {

    public static void main(String[] args) {
        int arr[] = {3, 9,-1, 10, -2};

        int temp = 0;
        boolean flag = false;//作为标识符，表示是否交换过
        for (int j = 0; j < arr.length - 1 ; j++) {

            //为了容易理解，把数据放出来看
            for (int i = 0; i < arr.length - 1 - j; i++) {
                //如果前面的数比之后的大，则交换
                if(arr[i] >arr[i + 1]){
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }

            }
            System.out.printf("第%d次排序结果：",j);
            System.out.println(Arrays.toString(arr));
        if(!flag){
            break;
        }else {
            flag = false;//用于重置flage用于下次循环使用
        }
        }
    }

}
