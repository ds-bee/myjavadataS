package com.huang.sort;

public class MergetSort {

    public static void main(String[] args) {

    }
    //归并排序(分治思想)
    public static void mergeSort(int[] arr, int left, int right,int[] temp){
        if(left < right){
            //找到中间位置
            int mid = (left + right)/2;
            //分 将数组完全进行左右互分，拆成一个一个的
            //
            mergeSort(arr, left, mid, temp);
            //因为右边的数在mid的右边，所以要加一，避免重复
            mergeSort(arr,mid + 1, right,temp);
            //这里实际上是将原数组那个拆分了
            //治
            //因为实际上是递归的方法，如果传入的是111，就是最底层的数组，就会不满足条件退出
            //递归到上一部分，实际上数组也没有被分，是通过mid来判断分层的几组
            //这里这个使用mid而不是用新数组的思想可以学习一下
            merge(arr,left,mid,right,temp);



        }
    }
    public static void merge(int[] arr, int left,int mid, int right,int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;

        //将传入的数组赋值给temp，通过arr[i] <= arr[j]，就相当于排好顺序
        //同时判断条件是遍历完分治的一边
        while (i <= mid && j<=right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {
                temp[t] = arr[j];
                t += 1;
                j += 1;

            }
        }
        //这个是将剩余的数据放入数组中
        while (i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        //同上
        while (j <= right){
            temp[t] = arr[j];
            t += 1;
            i += 1;
        }
        //这里是将暂存数组中的保存回到原数组arr
        t = 0 ;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

}
