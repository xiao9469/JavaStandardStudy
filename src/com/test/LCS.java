package com.test;



//使用动态规划找出最长公共子序列
public class LCS {
	//StringBuilder str = new StringBuilder();
	
    public static void main(String[] args) {
        String x  = "BDCABA";
        String y  = "ABCBDAB";

        int m = x.length();
        int n = y.length();
        //创建二维数组，也就是填表的过程
        int[][] c = new int[m+1][n+1];

        //初始化二维数组
        for (int i = 0; i < m+1; i++) {
            c[i][0] = 0;
        }
        for (int i = 0; i < n+1; i++) {
            c[0][i] = 0;
        }

        //实现公式逻辑
        int[][] path = new int[m+1][n+1];//记录通过哪个子问题解决的，也就是递推的路径
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(x.charAt(i-1) == y.charAt(j-1)){
                    c[i][j] = c[i-1][j-1] + 1;
                }else if(c[i-1][j] >= c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    path[i][j] = 1;
                }else{
                    c[i][j] = c[i][j-1];
                    path[i][j] = -1;
                }
            }
        }
        //输出查看c
        System.out.println("c:");
        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < n+1; j++) {
                System.out.print(c[i][j]+"\t");
            }
            System.out.println();
        }
        //输出查看path
        System.out.println("path:");
        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < n+1; j++) {
                System.out.print(path[i][j]+"\t");
            }
            System.out.println();
        }
        LCS l= new LCS();
        System.out.printf("%s与%s的最长公共子序列为：\n",x,y);
        String s = new String();
        PrintLCS(path,x,m,n,s);
        
        

    }


    public static void PrintLCS(int[][]b,String x,int i,int j ,String s){
        if(i == 0 || j == 0){
        	//System.out.println(s.length());
            return;
        }

        if(b[i][j] == 0){
            PrintLCS(b,x,i-1,j-1,s);
            System.out.printf("%c",x.charAt(i-1));
            s = s + x.charAt(i-1);
            
        }else if(b[i][j] == 1){
            PrintLCS(b,x,i-1,j,s);
        }else{
            PrintLCS(b,x,i,j-1,s);
        }
        
    }
}
