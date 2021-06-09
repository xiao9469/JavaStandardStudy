package com.test;

import com.obj.Person;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.IntToDoubleFunction;


public class TestReviewClass {

    @Test
    public void TestCreateObjectMethod(){
        //反射获取类的对象
        try {
            Class<Person> clazz = Person.class;
            Person personObject = clazz.newInstance();
            System.out.println(personObject);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestLoadPropertiesClassloaderMethod(){
        try {
            //用ClassLoader加载读取配置文件
            ClassLoader clazzLoader = TestReviewClass.class.getClassLoader();
            InputStream inputStream = clazzLoader.getResourceAsStream("1.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String str1 = properties.getProperty("name4");
            System.out.println(str1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestLoadPropertiesMethod(){
        FileInputStream fileInputStream = null ;
        try {
            File file = new File("Test.properties");
            fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String str1 = properties.getProperty("name5");
            System.out.println(str1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
