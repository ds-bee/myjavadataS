package com.huang.sort;

public class QuickSort {

    public static void main(String[] args) {

    }

    public static void quicSort(int[] arr,int left, int right){
        int l = left;
        int r = right;

        int piuot = arr[(left + right)/2];
        int tem;
        while (l < r){
            //找到左边大于等于中间这个数
            while (arr[l] < piuot){
                l += 1;
            }
            //找到右边小于这个数的
            while (arr[r] > piuot){
                r -= 1;
            }
            if( l >= r){
                break;
            }
            //找到要交换的两个数的索引了，然后交换他们
            tem = arr[l];
            arr[l] = arr[r];
            arr[r] = tem;
            //如果左边的数等于中间数，因为两边的数已经找好了
            //如果有一样的数，循环结束，就要将左右两边一样的索引给减去
            //这里还有个情况，就是arr[l]和arr[r]等于时已经交换
//            这里就是判断等于的情况，如果左边的相等
            if(arr[l] == piuot){
                r -= 1;
            }
            if(arr[r] == piuot){
                l +=1;
            }
        }
        //当左右下标一样，就是有相等的情况出现，需要跳过中间数(这是自己想的)
        //这个是防止栈溢出，因为如果r和l相等，就不会进入循环，下面调用递归回一直递归下去
        if(l == r){
            l += 1;
            r -= 1;
        }
        //这里是向左遍历，r是中间数左边的
        if(left < r){
            quicSort(arr,left,r);
        }
        if(right>l){
            quicSort(arr,l,right);
        }
    }

}
