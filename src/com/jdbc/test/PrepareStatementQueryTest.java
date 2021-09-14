package com.jdbc.test;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementQueryTest {

    @Test
    public void TestMethod(){
        String sql = "select id,name,email,birth from customers where id <?";
        List<Customers>customersList = PrepareStatementMutiQueryTestMethod(Customers.class, sql, 10);
        for (Customers customers : customersList) {
            System.out.println(customers);
        }


        /*String sql2 = "select order_id orderID,order_name orderName,order_date orderDate from `order`where order_id < ?";
        List<Order> orderList = PrepareStatementMutiQueryTestMethod(Order.class, sql2, 3);
        System.out.println(orderList);*/
    }

    public <T> List<T> PrepareStatementMutiQueryTestMethod(Class<T> clazz, String sql, Object...args){
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
            for(int i =0;i<args.length;i++){
                ps.setObject(i+1, args[i]);
            }
            //获取结果集
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过结果集 获取表的列数
            int columnCount = metaData.getColumnCount();
            //创建对象结合
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()){
                //创建T对象
                T t = clazz.newInstance();
                //处理一行结果中的每一个数据
                for(int i =0;i<columnCount;i++){
                    //获取i+1列的列名【不推荐使用】   列名是跟元数据相关的
                    //String colunmName = metaData.getColumnName(i+1);
                    //获取i+1列的别名【√】
                    String colunmLabelName = metaData.getColumnLabel(i+1);
                    //获取i+1列的值     列值是跟数据集相关的
                    Object columnValue = resultSet.getObject(i+1);
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

    //Class<T>带泛型参数T   指明了返回值类型只能是T    方法就是泛型方法
    public <T>T PrepareStatementQueryTestMethod(Class<T> clazz,String sql,Object...args){
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
            for(int i =0;i<args.length;i++){
                ps.setObject(i+1, args[i]);
            }
            //获取结果集
            resultSet = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过结果集 获取表的列数
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()){
                //创建T对象
                T t = clazz.newInstance();
                //处理一行结果中的每一个数据
                for(int i =0;i<columnCount;i++){
                    //获取i+1列的列名【不推荐使用】   列名是跟元数据相关的
                    //String colunmName = metaData.getColumnName(i+1);
                    //获取i+1列的别名【√】
                    String colunmLabelName = metaData.getColumnLabel(i+1);
                    //获取i+1列的值     列值是跟数据集相关的
                    Object columnValue = resultSet.getObject(i+1);
                    //给column对象指定columnName属性
                    Field field = clazz.getDeclaredField(colunmLabelName);
                    field.setAccessible(true);
                    //将columnValue值放到columnName中并封装在customes对象里
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }
}


