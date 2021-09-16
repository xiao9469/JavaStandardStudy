package com.jdbc.dao;

import com.jdbc.test.JDBCUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
* 针对数据表的通用操作方法封装
* */

//虽然没有抽象方法，但是标注abstract指明这个类不能创建对象
public abstract class BaseDao {

    //针对select count(*) 这一类返回一行一列的方法
    public <T>T getValue(Connection connection,String sql,Object ...args){
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<args.length;i++){
                preparedStatement.setObject(i+1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return (T) resultSet.getObject(1);
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //注意结尾不要把connection关了
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;

    }
    //针对不同表的增删改方法
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
    //针对不同表的通用查询方法
    public <T> List<T> PrepareStatementMutiQueryTestMethod(Class<T> clazz, String sql, Object...args) {
        //针对不同表通用的查询参数
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //预编译sql语句
            ps = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //获取结果集
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过结果集 获取表的列数
            int columnCount = metaData.getColumnCount();
            //创建对象结合
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                //创建T对象
                T t = clazz.newInstance();
                //处理一行结果中的每一个数据
                for (int i = 0; i < columnCount; i++) {
                    //获取i+1列的列名【不推荐使用】   列名是跟元数据相关的
                    //String colunmName = metaData.getColumnName(i+1);
                    //获取i+1列的别名【√】
                    String colunmLabelName = metaData.getColumnLabel(i + 1);
                    //获取i+1列的值     列值是跟数据集相关的
                    Object columnValue = resultSet.getObject(i + 1);
                    //给column对象指定columnName属性
                    Field field = clazz.getDeclaredField(colunmLabelName);
                    field.setAccessible(true);
                    //将columnValue值放到columnName中并封装在customes对象里
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }

}