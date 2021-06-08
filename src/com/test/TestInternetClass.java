package com.test;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestInternetClass {
    //服务端
    @Test
    public void TestServerClass(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null ;

        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(8899);
            //2.调用accept()表示接收来自于客户端的socket
            socket = serverSocket.accept();
            //3.获取输入流
            inputStream = socket.getInputStream();
            //4.读取输入流中的数据
            byteArrayOutputStream = new ByteArrayOutputStream();

            //使用byteArrayOutputSrteam读，不会乱码
            byte[] dataBuffer = new byte[1024];
            int dataIndex;
            while ((dataIndex = inputStream.read(dataBuffer))!= -1){
                byteArrayOutputStream.write(dataBuffer, 0, dataIndex);
            }
            System.out.println(byteArrayOutputStream.toString());

            System.out.println(socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byteArrayOutputStream.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //客户端
    @Test
    public void TestCustomerClass(){
        InetAddress inetAddress = null;
        Socket socket = null ;
        OutputStream outputStream = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 8899);
            //2.获取一个输出流，用于输出数据
            outputStream = socket.getOutputStream();
            //3.写出数据的操作
            outputStream.write("abcdef".getBytes());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (socket != null){

                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void TestInternetMethod(){
        InetAddress inetAddress = null;
        InetAddress inetAddress2 = null;
        InetAddress inetAddress3 = null;
        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
            inetAddress2 = InetAddress.getByName("baidu.com");
            //获取本地IP
            inetAddress3 = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(inetAddress);
        System.out.println(inetAddress2+"  主机名："+inetAddress2.getHostName() +
                "  主机地址："+inetAddress2.getHostAddress());
        System.out.println("主机名："+inetAddress3);
    }
}
