package com.test;

import org.junit.Test;

import java.io.File;

public class TestFileClass {

    @Test
    public void TestFileMethod(){
        //构造器1
        //相对路径
        File file_1 = new File("hello.txt");
        //绝对路径
        File file_2 = new File("E:\\IdeaItem\\test\\Test.properties");
        //动态分隔符
        File file_3 = new File("E:"+File.separator+"IdeaItem"+File.separator+"test"+File.separator+"Test.properties");
        //构造器2  参数一是主路径，参数二可以是文件可以是目录
        File file_4 = new File("E:\\IdeaItem","test");
        //构造器3
        File file_5 = new File(file_4, "hi.txt");
        //Test
        File file_6 = new File("E:\\闪电  下载");

        File[] files = file_6.listFiles();
        for (int i = 0;i < files.length;i++){
//            System.out.print(i+":");
//            System.out.print(files[i].getName());
            System.out.println(i+":"+files[i].getName());
            if (i == 6){
                System.out.println(files[6].length()/1024+"KB");
            }
        }


    }
    @Test
    public void test4(){
        File file1 = new File("hello.txt");
        File file2 = new File("E:\\IdeaItem\\hello.txt");

        boolean renameTo = file1.renameTo(file2);
        System.out.println(renameTo);

    }
}
