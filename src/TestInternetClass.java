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
            serverSocket = new ServerSocket(8899);
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] dataBuffer = new byte[5];
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
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 8899);
            outputStream = socket.getOutputStream();
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
