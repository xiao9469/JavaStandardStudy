package com.test;



import com.obj.TestNormalObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestIterator {
    @Test
    public void TestIteratorMethod(){
        Collection collection = new ArrayList();
        collection.add(123);
        collection.add(new String("abc"));
        collection.add(new TestNormalObject("1",2));

        new TestNormalObject();
        Iterator iterator = collection.iterator();

        //遍历的方式——使用while
        while (iterator.hasNext()){
            Object o = iterator.next();
            if ("abc".equals(o)){
                iterator.remove();
            }
        }

       /*
       //错误：
        while ((iterator.next()) != null){
            System.out.println(iterator.next());
        }



        错误
        while (collection.iterator().hasNext()){
            System.out.println(collection.iterator().next());
        }
        */

    }
    
}
