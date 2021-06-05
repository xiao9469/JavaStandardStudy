package com.test;

import com.obj.TestNormalObject;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestSetClass {
    @Test
    public void TestSetMethod(){
        //定制排序
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof  TestNormalObject && o2 instanceof  TestNormalObject){
                    TestNormalObject t1 = (TestNormalObject)o1;
                    TestNormalObject t2 = (TestNormalObject)o2;
                    return Integer.compare(t1.getID(),t2.getID());
                }else {
                    throw new RuntimeException("输入类型不匹配");
                }
            }
        };
        Set set = new TreeSet(com);
        set.add(new TestNormalObject("abc", 5));
        set.add(new TestNormalObject("wt", 8));
        set.add(new TestNormalObject("qq", 3));
        set.add(new TestNormalObject("cdf", 1));
        set.add(new TestNormalObject("cdf", 2));

        System.out.println(set);


    }
}
