package com.stocknap;


import java.util.Map;

public class Main {

    int [][] arr ;


    /*
    {
    {1,1}
    {1,0}   X {}
    }

     */


    public static int[][] nthFibonachi( int n){
        if (n ==1 ){
            return new int[][]{{1,1},{1,0}};
        }

        if (n ==0 ){
            return new int[][]{{0,0},{0,0}};
        }



        if (n % 2 ==0){
            // n is even , we need to dpo divide by 2
           int[][] m2= Main.nthFibonachi( n/2);
           return matMultiply(m2,m2);

        }
        else{
            //int[][] m2= Main.nthFibonachi( (n+1)/2);
            int [][] m3 = Main.nthFibonachi((n-1)/2);
            return matMultiply(matMultiply(m3,m3),new int[][]{{1,1},{1,0}});
        }


    }

    public static int[][] matMultiply(int [][] m1 , int[][] m2){
        int[][]  m3  = new int[2][2];
        for (int i = 0 ; i < 2 ; i++){
            for(int j = 0 ; j < 2 ; j++){
                m3[i][j]= 0;
                for (int k= 0 ; k < 2 ; k++){
                   m3[i][j]= m3[i][j]+m1[i][k]* m2[k][j];
                }
            }
        }

        /*
        printing matrix multiply


        System.out.println("--------------m1---------");
        for(int i  = 0  ; i< m1.length ;  i++){
            for(int j = 0 ; j< m1[0].length; j++){
                System.out.print(m1[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--------------m1---------");


        System.out.println("--------------m2---------");
        for(int i  = 0  ; i< m2.length ;  i++){
            for(int j = 0 ; j< m2[0].length; j++){
                System.out.print(m2[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--------------m2---------");


        System.out.println("--------------m3---------");
        for(int i  = 0  ; i< m3.length ;  i++){
            for(int j = 0 ; j< m3[0].length; j++){
                System.out.print(m3[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("--------------m3---------");
         */

        return m3;
    }

    public static void runFibonachi(){
        // write your code here

        System.out.println(0+"  : "+ 0);
        System.out.println(1+"  : "+1);
        for(int i = 2  ;  i< 10 ; i ++){
            System.out.println(i+"  : "+nthFibonachi(i-1)[0][0]);
        }

    }

    public static void main(String[] args) {
	        //runFibonachi();
        //Multithreading.runFlow(args);
        MultithreadingCommunication.runFlow(args);
    }
}
