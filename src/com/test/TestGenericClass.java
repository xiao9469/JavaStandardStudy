package com.test;

import com.obj.TestGenericSelfDefindObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//泛型学习
public class TestGenericClass {

    public void m1(List<String> list){

    }
    public void m2(List<Object> list){

    }
    @Test
    public void testMethod(){
        List<Object> list1 = null;
        List<String >list2 = null;

        List<?> list = null;
        list = list1;
        list = list2;


    }

    public void print(List<?> list){
        for (Object o : list) {
            System.out.println(o);
        }
    }

}

