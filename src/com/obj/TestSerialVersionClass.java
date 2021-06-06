package com.obj;

import java.io.Serializable;

public class TestSerialVersionClass implements Serializable {
    public static final long serialVersionUID = 1231312435345L;

    private String name;
    private int age;

    @Override
    public String toString() {
        return  "{"+
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public TestSerialVersionClass() {
    }

    public TestSerialVersionClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
