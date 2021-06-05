package com.test;

import org.junit.Test;

import java.io.*;

public class TestBufferedStreamClass {
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
