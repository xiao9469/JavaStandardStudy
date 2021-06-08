package com.test;

import com.obj.Person;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflectionClass {
    @Test
    public void TestMethod1(){
        Class personClass = Person.class;
        try {
            //获取反射构造方法对象           //construction代表类的构造器
            Constructor constructor = personClass.getConstructor(String.class,int.class);
            //构造对象
            Object o = constructor.newInstance("UU", 66);
            //获取反射对象指定的属性对象       Field代表成员变量
            Field age = personClass.getDeclaredField("age");
            age.set(o, 100);
            System.out.println(o);
            //调用反射对象指定的方法对象       Method代表类的成员方法
            Method show = personClass.getDeclaredMethod("show");
            show.invoke(o);

            //通过反射，可以调用Person类的私有构造结构
            Constructor constructor1 = personClass.getDeclaredConstructor(String.class);
            constructor1.setAccessible(true);
            Person p1 = (Person) constructor1.newInstance("YYY");
            System.out.println(p1);

            //通过反射，调用Person类的私有属性
            Field name = personClass.getDeclaredField("name");
            name.setAccessible(true);
            name.set(p1, "HMM");
            System.out.println(p1);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}


