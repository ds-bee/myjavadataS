package com.huang.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        //二分查找必须是有序数组
        int arr[] = {1, 7, 8, 345, 1234};
        int i = binarySeach(arr, 0, arr.length - 1, 7);
        System.out.println(i);


    }
    public static int binarySeach(int[] arr, int left, int right, int findVal){
        int mid = (left + right)/2;
        int midVal = arr[mid];
        if(left > right){
            return -1;
        }
        if(findVal > midVal){
            return binarySeach(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            return binarySeach(arr,left,mid -1 ,findVal);
        }else{
            return mid;
        }
        //没有终止条件会产生死递归

    }
    //如果有多个一样值，需要找出不同的下标
    public static List<Integer> binarySeach1(int[] arr, int left, int right, int findVal){
        int mid = (left + right)/2;
        int midVal = arr[mid];
        if(left > right){
            return new ArrayList<Integer>();
        }
        if(findVal > midVal){
            return binarySeach1(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            return binarySeach1(arr,left,mid -1 ,findVal);
        }else{
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(mid);
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                integers.add(temp);
                temp--;

            }
//            int temp1 = mid + 1;
            temp = mid + 1;
            while (true){
                if(temp< 0 || arr[temp] != findVal){
                    break;
                }
                integers.add(temp);
                temp++;
            }
            return integers;
        }
        //没有终止条件会产生死递归

    }


}
