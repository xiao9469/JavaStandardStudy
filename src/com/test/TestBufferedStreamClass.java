package com.test;

import org.junit.Test;

import java.io.*;

public class TestBufferedStreamClass {
    @Test
    public void TestReadLineMethod(){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("1.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("2.txt")));

           String data;
           while ((data = bufferedReader.readLine()) != null){
               //readLine方法默认是不换行的，所以要手动换行
               bufferedWriter.write(data + "\n");
               //换行方式二
               bufferedWriter.newLine();
           }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (bufferedWriter != null){
                        try {
                            bufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
    @Test
    public void TestBufferedReadMethod(){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(new File("1.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("2.txt")));

            char[] dataArr = new char[10];
            int dataindex ;
            while ((dataindex = bufferedReader.read(dataArr)) != -1){
                bufferedWriter.write(dataArr, 0, dataindex);
                bufferedWriter.flush();
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (bufferedWriter != null){
                        try {
                            bufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
    @Test
    public void TestBufferedStreamMethod(){
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(
                    new File("1.jpg")));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(
                    new File("1_copy.jpg")));
            byte [] dataBytes = new byte[20];
            int dataIndex ;
            while ((dataIndex = bufferedInputStream.read(dataBytes)) != -1){
                bufferedOutputStream.write(dataBytes, 0, dataIndex);
                //如果显示的调用，在没有存满的情况下，会自动给你刷新当前的缓冲区
                bufferedOutputStream.flush();
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (bufferedInputStream != null ){
                    bufferedInputStream.close();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bufferedOutputStream != null){
                        bufferedOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
