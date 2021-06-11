package com.obj;

public class Son extends Father {
    @Override
    public void show() {
        //super.show();
        System.out.println("son show");
    }

    public static void main(String[] args) {
        Father f = new Son();
        f.show();


    }
}
