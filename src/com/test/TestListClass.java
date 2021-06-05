package com.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestListClass {
    @Test
    public void TestListMethod(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
       // list.remove();

        System.out.println(list);

    }
}
