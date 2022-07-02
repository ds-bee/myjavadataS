package com.huang.recursion;

public class MiGong {

    public static void main(String[] args) {
        //创建一个迷宫
        int[][] map = new int[8][7];
        //将周围的砖块置1
        for (int i = 0; i <= 6; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i <= 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        map[3][1] = 1;
        map[3][2] = 1;
        		map[1][2] = 1;
		map[2][2] = 1;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 6; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        setWay(1,1,map);
        System.out.println("------------------");
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 6; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }


    }

    public static boolean setWay(int i,int j,int[][] map){

        if(map[6][5] == 2){
            return true;
        }else {
            map[i][j] = 2;
            if(map[i][j] == 2){
                if(map[i][j + 1] == 0){
                    setWay(i, j+1,map);
                    return true;
                }else if(map[i +1][j ] == 0){
                    setWay(i+1, j,map);
                    return true;
                }else if(map[i -1][j ] == 0){
                    setWay(i-1, j,map);
                    return true;
                }else if(map[i ][j -1] == 0){
                    setWay(i, j-1,map);
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }

    }
}
