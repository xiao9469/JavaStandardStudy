package com.test;

import org.junit.Test;

import java.util.Comparator;

public class TestLambdaClass {
    @Test
    public void TestLambdaMethod2(){
        //正常写函数
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int comparaResult1 = comparator1.compare(2, 3);
        System.out.println(comparaResult1);

        //Lambda表达式写法
        Comparator<Integer> comparator2 = ((o1, o2) -> Integer.compare(o1, o2));
        int comparatorResult2 = comparator2.compare(2, 3);
        System.out.println(comparatorResult2);

        //方法引用的写法
        Comparator<Integer> comparator = Integer :: compare;
        int compara4 =  comparator.compare(1, 2);
        System.out.println(compara4);
    }
    @Test
    public void TestLambdaMethod1(){
        //正常写函数
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("测试1");
            }
        };
        runnable1.run();

        //lambda表达式写函数
        Runnable runnable2 = () -> System.out.println("测试2");
        runnable2.run();
    }
}
