package com.test;

import com.obj.Person;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

public class TestClassInstantsClass {

    @Test
    public void TestMethod(){

        try {
            Class<Person> clazz = Person.class;
            Person obj = clazz.newInstance();
            System.out.println(obj);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void LoadPropertiesMethod(){

        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            //方式一
            //读的是module目录下的文件
            fileInputStream = new FileInputStream(new File("Test.properties"));
            properties.load(fileInputStream);

            String name = properties.getProperty("name11");
            String id = properties.getProperty("ID");

            System.out.println(name);
            System.out.println(id);




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //方式二
            ClassLoader classLoader = TestClassInstantsClass.class.getClassLoader();
            //读的是src目录下的文件
            InputStream is = classLoader.getResourceAsStream("1.properties");
            properties.load(is);

            String name = properties.getProperty("name11");
            String id = properties.getProperty("ID");

            System.out.println(name);
            System.out.println(id);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void GetInstantsMethod(){

        try {

            //方式1  调用运行时类的属性
            Class<Person> clazz = Person.class;
            System.out.println(clazz);
            //方式2  调用getClass方法
            Person p1 = new Person();
            Class clazz2 = p1.getClass();
            //方式3 调用Class的静态方法：forName(String classPath)  这里的参数使用类的全类名
            Class clazz3 = Class.forName("java.lang.String");
            System.out.println(clazz3);

            //方式4
            ClassLoader classLoader = TestClassInstantsClass.class.getClassLoader();
            Class clazz4 = classLoader.loadClass("com.obj.Person");
            System.out.println(clazz4);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
