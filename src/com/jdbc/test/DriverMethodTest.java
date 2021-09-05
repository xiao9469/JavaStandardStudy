package com.jdbc.test;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverMethodTest {
    @Test
    public void connectDriverMethodTest() throws Exception{
        /*
        Driver driver = new com.mysql.jdbc.Driver();
        //URL是访问mysql的实际地址
        //jdbc主协议   mysql子协议   整个jdbc:mysql充当类似http协议头
        String url = "jdbc:mysql://localhost:3306/test";
        //将用户密码封装在properties文件中
        Properties infoProperties = new Properties();
        infoProperties.setProperty("user", "root");
        infoProperties.setProperty("password", "root");
        Connection connection = driver.connect(url,infoProperties);
        System.out.println(connection);
        */
        //方式二
        //使用动态代理的方式获取到驱动对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //设置访问URL
        String URL = "jdbc:mysql://localhost:3306/test";
        Properties infoProperties = new Properties();
        infoProperties.setProperty("user", "root");
        infoProperties.setProperty("password", "root");
        Connection connection = driver.connect(URL, infoProperties);
        System.out.println(connection);

    }
    //使用driverManager来代替driver的使用
    @Test
    public void driverManagerConnectMethodTest() throws Exception {
        //获取驱动对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //使用drivermanager进行注册驱动对象
        DriverManager.registerDriver(driver);
        //基本参数信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        //连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    //使用读取配置文件的方式
    @Test
    public void loadJdbcPropertiesConnected() throws IOException, ClassNotFoundException, SQLException {

        //1.读取配置文件中的4个基本信息
        InputStream is = DriverMethodTest.class.getClassLoader().
                getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
    @Test
    public void preparedStatementCreateMethodTest() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

}
