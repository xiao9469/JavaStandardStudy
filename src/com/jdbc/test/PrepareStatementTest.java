package com.jdbc.test;


import org.junit.Test;
import sun.net.www.MeteredStream;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class PrepareStatementTest {
    @Test
    public void QueryMethodTest(){
        String sql = "select id,name,email,birth from customers where id < ?";
        Customers customers = QueryMethod(sql, 10);
        System.out.println(customers);
    }

    public Customers queryForCustomers(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            //获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            while (rs.next()){
                Customers cust = new Customers();
                //处理结果集一行数据中的每一个列
                for(int i = 0;i <columnCount;i++){
                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的列名
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = Customers.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust, columValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.closeResource(conn, ps, rs);

        }

        return null;


    }

    //@Test
    public  Customers  QueryMethod(String sql,Object...args){
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
                //创建customer对象
                Customers customers = new Customers();
                //处理一行结果中的每一个数据
                for(int i =0;i<columnCount;i++){
                    //获取i+1列的列名【不推荐使用】   列名是跟元数据相关的
                    //String colunmName = metaData.getColumnName(i+1);
                    //获取i+1列的别名【√】
                    String colunmLabelName = metaData.getColumnLabel(i+1);
                    //获取i+1列的值     列值是跟数据集相关的
                    Object columnValue = resultSet.getObject(i+1);
                    //给column对象指定columnName属性
                    Field field = Customers.class.getDeclaredField(colunmLabelName);
                    field.setAccessible(true);
                    //将columnValue值放到columnName中并封装在customes对象里
                    field.set(customers, columnValue);
                }
                return customers;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, ps, resultSet);
        }
        return null;
    }
    //测试通用方法
    @Test
    public void commonMethodTest(){
        String sql = "update customers set email =? where id = ?";
        Update(sql, "666@666.test",5);
    }

    //通用的增删改操作  使用多个参数直接传参
    public void Update(String sql,Object ...args){
        Connection connection = null ;
        PreparedStatement preparedStatement = null ;
        try {
            //获取数据库连接
            connection = JDBCUtils.getConnection();
            //预编译sql语句
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i<args.length; i++){
                preparedStatement.setObject(i+1, args[i]);
            }
            //执行
            preparedStatement.execute();
            //关流
            JDBCUtils.closeResource(connection, preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改表中的一条数据
    @Test
    public void UpdateTest(){
        //获取数据库连接
        Connection connection = null;
        PreparedStatement preparedStatement = null ;
        try {
            connection = JDBCUtils.getConnection();
            //预编译sql语句
            String sql = "update customers set name = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            //填充占位符
            preparedStatement.setObject(1, "测试");
            preparedStatement.setObject(2, "22");
            //执行
            preparedStatement.execute();
            //关流
            JDBCUtils.closeResource(connection, preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Method2Test(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.读取配置文件中的4个基本信息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            // 2.加载驱动
            Class.forName(driverClass);

            // 3.获取连接
            conn = DriverManager.getConnection(url, user, password);

//		System.out.println(conn);

            //4.预编译sql语句，返回PreparedStatement的实例
            String sql = "insert into customers(name,email,birth)values(?,?,?)";//?:占位符
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setString(1, "11111111");
            ps.setString(2, "1231111111@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-01-01");
            ps.setDate(3,new java.sql.Date(date.getTime()));

            //6.执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
    }
}
