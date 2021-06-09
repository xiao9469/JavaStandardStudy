package com.obj;

public class Person {
    private String name;
    public int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public Person() {

    }

    public void show(){
        System.out.println("Person类的show方法");
    }

    private String showNation(String nation){
        System.out.println("地域：" + nation);
        return nation;
    }
}
