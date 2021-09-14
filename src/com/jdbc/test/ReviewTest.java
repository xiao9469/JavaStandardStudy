package com.jdbc.test;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ReviewTest {
    @Test
    public void testMethod(){
        String sql = "select id,name,email,birth from customers where id < ?";
        try {
            List list = QueryMethod(Customers.class, sql, 5);
            for (Object o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    }
    public <T>List  QueryMethod(Class<T> clazz,String sql,Object...args) throws Exception {
        Connection connection = null ;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for(int i =0;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
            //获取数据集
            resultSet = preparedStatement.executeQuery();
            //获取元数据集
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取列数
            int columnCount = metaData.getColumnCount();
            //创建一个对象集合
            ArrayList<Object> list = new ArrayList<>();
            //获取每一列的数据
            while(resultSet.next()){
                T t = clazz.newInstance();
                for (int i =0;i<columnCount;i++){
                    //获取列名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //获取列值
                    Object columnValue = resultSet.getObject(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }
        return null;
    }
}
