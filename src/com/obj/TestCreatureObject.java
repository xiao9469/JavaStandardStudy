package com.obj;

import java.io.Serializable;

public class TestCreatureObject<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("breath");
    }
    public void eat(){
        System.out.println("eat");
    }

}
