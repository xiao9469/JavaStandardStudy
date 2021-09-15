package com.jdbc.test;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlogMethodTest {
    @Test
    public void MethodGetBlogTest() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth,photo from customers where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 342);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Blob photo = resultSet.getBlob("photo");
            InputStream is = photo.getBinaryStream();
            FileOutputStream fileOutputStream = new FileOutputStream("123123.jpg");
            byte[] byteArr = new byte[1024];
            int len = 0;
            while ((len = is.read(byteArr)) != -1){
                fileOutputStream.write(byteArr, 0, len);
            }

        }
        JDBCUtils.closeResource(connection, preparedStatement,resultSet);
    }
    @Test
    public void MethodTest() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into customers(id,name,email,birth,photo)values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        FileInputStream fileInputStream = new FileInputStream(new File("acv.jpg"));
        preparedStatement.setObject(1, 342);
        preparedStatement.setObject(2, "342");
        preparedStatement.setObject(3, "123@1231");
        preparedStatement.setObject(4, "1999-09-09");
        preparedStatement.setObject(5, fileInputStream);
        preparedStatement.execute();
        JDBCUtils.closeResource(connection, preparedStatement);
    }
}
