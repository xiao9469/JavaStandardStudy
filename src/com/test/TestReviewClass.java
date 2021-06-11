package com.test;

import com.obj.Person;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.IntToDoubleFunction;


public class TestReviewClass {

    @Test
    public void TestGetNameMethod(){
        //获取属性名
        try {
            Class clazz = Class.forName("com.obj.Person");
            Field[] declareFields = clazz.getDeclaredFields();
            for (Field declareField : declareFields) {
                String name = declareField.getName();
                System.out.println(name);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestGetTypeMethod(){
        //获取属性的类型
        try {
            Class clazz = Class.forName("com.obj.Person");
            Field[] declareFields = clazz.getDeclaredFields();
            for (Field declareField : declareFields) {
                Class type = declareField.getType();
                System.out.println(type.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestGetModifiers(){
        //获取属性的修饰符
        try {
            Class clazz = Class.forName("com.obj.Person");
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //权限修饰符
                int modify = declaredField.getModifiers();
                //   \t代表着一个制表符
                System.out.print(Modifier.toString(modify) + "\t");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestGetFieldsMethod(){
        //获取属性的修饰符和属性全类名
        try {
            Class clazz = Class.forName("com.obj.Person");
            //获取public属性结构
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                System.out.println(field);
            }
            System.out.println("----------------------------------------");
            //获取当前类声明的所有属性【不包含父类声明的属性】
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestCreateObjectMethod2(){
        //反射获取类的对象  方式二
        try {
            Class clazz = Class.forName("com.obj.Person");
            Object object = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestCreateObjectMethod(){
        //反射获取类的对象  方式一
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
