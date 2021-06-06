package com.test;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;


public class TestReadWriteClass {

    @Test
    public void TestPropertiesMethod(){
        File file = new File("Test.properties");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = properties.getProperty("name10");
        System.out.println("name10: " + s);

    }
    @Test
    public void TestReadWriteMethod(){
        FileReader fileReader = null;
        try {
            File file = new File("1.txt");
            System.out.println(file.getAbsolutePath());//E:\IdeaItem\test\hello

            fileReader = new FileReader(file);
            int dataRead;
            while ((dataRead = fileReader.read())!= -1){

                System.out.println(dataRead+"-"+(char)dataRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //为了避免创建流的时候抛了异常
                if (fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
        写法二
        int dataRead_2 = fileReader.read();
        while (dataRead_2 != -1 ){
            System.out.println((char)dataRead_2);
            dataRead_2 = fileReader.read();
        }
         */


    }

    @Test
    public void TestReadBuffMethod(){
        FileReader fileReader = null;
        try {
            File file = new File("2.txt");
            fileReader = new FileReader(file);
            //使用char作为缓存，指定每一次读多少个
            char [] readArr = new char[4];
            int readData ;
            while ((readData = fileReader.read(readArr)) != -1){

                String str = new String(readArr, 0, readData);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void TestWriteMethod(){
        File file = new File("2.txt");
        try {
            //尾部追加
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write("123");
            fileWriter.write("abc");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file2 = new File("2.txt");
            FileReader fileReader = new FileReader(file2);
            char[] buffData = new char[10];
            int indexData ;
            while ((indexData = fileReader.read(buffData)) != -1){
                String s = new String(buffData, 0, indexData);
                System.out.println(s);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testCopyFileMethod(){
        FileReader fileReader = null;
       // FileReader fileReader2 = null;
        FileWriter fileWriter = null;

        try {
            File file1 = new File("1.txt");
            File file2 = new File("2.txt");
            //File file3 = new File("2.txt");


            fileReader = new FileReader(file1);
          //  fileReader = new FileReader(file3);
            fileWriter = new FileWriter(file2);

            int readIndex;
            char [] buffData = new char[5];
            while ((readIndex = fileReader.read(buffData)) != -1){
                 fileWriter.write(buffData, 0, readIndex);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileReader != null ){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(fileWriter != null){
                        fileWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
