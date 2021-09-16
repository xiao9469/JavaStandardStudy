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
    public void UpdateTX() throws Exception {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            //取消数据的自动提交
            connection.setAutoCommit(false);

            String sql_1 = "update user_table set balance = balance-100 where user=?";
            Update(connection, sql_1, "AA");
            System.out.println(10/0);
            String sql_2 = "update user_table set balance = balance+100 where user=?";
            Update(connection, sql_2, "BB");
            //提交数据
            connection.commit();
        } catch (Exception e) {

            e.printStackTrace();
            connection.rollback();
        } finally {
            //涉及到数据库连接池的时候，需要把自动提交打开再还回去
            connection.setAutoCommit(true);
            JDBCUtils.closeResource(connection, null);
        }
    }
    public void Update(Connection connection,String sql,Object ...args){
        PreparedStatement preparedStatement = null ;
        try {
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i<args.length; i++){
                preparedStatement.setObject(i+1, args[i]);
            }
            //执行
            preparedStatement.execute();
            //关流
            JDBCUtils.closeResource(null, preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
