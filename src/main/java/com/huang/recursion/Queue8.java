//package com.huang.recursion;
//
//public class Queue8 {
//
//    //定义最大皇后数
//    static int max = 8;
//    //��������array, ����ʺ����λ�õĽ��,���� arr = {0 , 4, 7, 5, 2, 6, 1, 3}
//    static int[] array = new int[max];
//    static int count = 0;
//    static int judgeCount = 0;
//    public static void main(String[] args) {
//
//        check(0,array);
//
//
//    }
//
//    static void check(int n, int[] array) {
//        for (int i = 0; i < 7; i++) {
//            array[n] = i;
//            if (judge(array, n)) {
//                check(n + 1,array);
//            }
//        }
//    }
//
//    static Boolean judge(int[] array,  int n){
//        if (n == 8){
//            print(array);
//            return false;
//        }
//        for (int i = 0; i < 8; i++) {
//            if((array[i] != array[n] ) && (Math.abs(i-n) != Math.abs(array[i]-array[n]))){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    public static void getmethod(int[] agr, int n){
//
//        int sum = 0;
//        if(sum == 8 ){
//            return;
//        }
//        for (int i = n; i < 8; i++) {
//            agr[n] = i;
//            for (int j = i  ; j < n; j++) {
//                if((i != j ) && (Math.abs(i-j) != Math.abs(agr[i]-agr[j]))){
//                    agr[ i+1 ] = j;
//                    getmethod(agr,n+1);
//                }
//                sum++;
//            }
//        }
//        print(agr);
//    }
//    private static void print(int[] array) {
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();
//    }
//}
