package com.test;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.IntToDoubleFunction;

//复习集合相关
public class TestReviewClass {

    @Test
    public  void TestSetMethod(){
        Set set = new HashSet();
        set.add(123);
        set.add("bb");
        set.add("qweq");

        for (Object o : set) {
            System.out.println(o);
        }

    }

    @Test
    public void TestLinkedListMethod(){
        List list = new LinkedList();
        list.add(123);
        list.add("qq");
        list.add("uvw");


    }
    @Test
    public void TestListMthod(){
        List list = new ArrayList();
        list.add(123);
        list.add("qq");
        list.add("uvw");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){

            if("uvw".equals(iterator.next())){
                iterator.remove();
            }
        }
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void testIteratorMethod(){
        Collection collection = new LinkedList();
        collection.add("qwe");
        collection.add(123);
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            System.out.println(o);
        }

    }
    @Test
    public void testArrayToColleticonMethod(){
        String [] strArr = new String[]{"q","w","e"};
        Collection collection = Arrays.asList(strArr);
        for (Object o : collection) {
            System.out.println(o);

        }
    }
    @Test
    public void testColletionToArrayMethod(){
        Collection collection = Arrays.asList(123,"str","test");
        Object[] objArr = collection.toArray();
        for (Object o : objArr) {
            System.out.println(o);
        }
    }
}
