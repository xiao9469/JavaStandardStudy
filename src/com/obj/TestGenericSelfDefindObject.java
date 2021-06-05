package com.obj;

import java.util.ArrayList;
import java.util.List;

public class TestGenericSelfDefindObject<T> {
    String name;
    int i;

    T testGenericObject;

    public TestGenericSelfDefindObject() {

    }

    public TestGenericSelfDefindObject(String name, int i, T t) {
        this.name = name;
        this.i = i;
        this.testGenericObject = t;
    }

    public T getTestGenericObject() {
        return testGenericObject;
    }

    public void setTestGenericObject(T testGenericObject) {
        this.testGenericObject = testGenericObject;
    }

    //泛型方法
    public static <E> List<E> TestArrayToListMethod(E [] arr){
        ArrayList<E> list = new ArrayList<E>();
        for (E e : list) {
            System.out.println(e);

        }
        return list;
    }
}

class subOrder extends TestGenericSelfDefindObject<String>{

}