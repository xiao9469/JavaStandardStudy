package com.test;

public class TestHanoi {
    //起点，辅助点，终点

    //核心的三步：
    /*
    1,把第1到n-1层移动到中间【这里以C为跳板】
    2,把第n层移动到最后【最后一层是可以直接移动的,因为是最大的一块】
    3,把第1到n-1层移动到最后【这里以A为跳板】
    */
    public static void hanio(int n,String A,String B,String C){
        if(n<1){
            System.out.println("汉诺塔的层数不得小于一");
        }
        else if(n==1){   //递归出口
            System.out.println("移动："+A+"——》"+C);
            return;
        }
        else{            //核心移动三步
            hanio(n-1,A,C,B);//A 绕过 C 放到 B 上面
            System.out.println("移动："+A+"——》"+C);
            hanio(n-1,B,A,C);
        }
    }
    public static void main(String args[]){
        String a = "a";
        String b = "b";
        String c = "c";
        hanio(3,a,b,c);
    }
}
