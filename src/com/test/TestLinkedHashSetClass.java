package com.test;

import org.junit.Test;

import javax.naming.Name;
import java.util.*;


public class TestLinkedHashSetClass {

    //entrySet方法
    @Test
    public void TestEntrySetMethod(){
        HashMap<String,Integer> map = new LinkedHashMap<String,Integer>();
        map.put("Tom",10);
        map.put("LL",1);
        map.put("UU",10);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String,Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> e = iterator.next();
            System.out.println(e+"===="+e.getClass().getName());
        }

    }

}
