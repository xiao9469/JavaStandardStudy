package com.test;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestInputStream {
    @Test
    public void TestFileInputStreamMethod(){
        FileInputStream fileInputStream = null ;
        FileOutputStream fileOutputStream = null;
        try {
            File picture1 = new File("1.jpg");
            File picture2 = new File("2.jpg");

            fileInputStream = new FileInputStream(picture1);
            fileOutputStream = new FileOutputStream(picture2);
            byte[] dataArr = new byte[5];
            int dataindex;
            while ((dataindex = fileInputStream.read(dataArr))!= -1){
                fileOutputStream.write(dataArr, 0, dataindex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null){
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
