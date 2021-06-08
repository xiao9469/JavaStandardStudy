package com.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFileClass {
    @Test
    public void TestRandomAccessFileMethod(){
        RandomAccessFile randomAccessFile = null;
        try {
            File file = new File("1.txt");
            randomAccessFile = new RandomAccessFile(file,"rw");
            randomAccessFile.seek(3);

            StringBuilder stringBuilder = new StringBuilder((int) new File("1.txt").length());
            int dataIndex;
            //char[] dataChars = new char[20];
            byte[] dataBytes = new byte[20];
            while ((dataIndex = randomAccessFile.read(dataBytes)) != -1){
                stringBuilder.append(new String(dataBytes,0,dataIndex));
            }
            randomAccessFile.seek(3);
            randomAccessFile.write("666abc".getBytes());
            randomAccessFile.write(stringBuilder.toString().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
