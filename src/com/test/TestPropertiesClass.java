package com.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestPropertiesClass {
    @Test
    public void testPropertiesMethod() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("Test.properties");
        properties.load(fileInputStream);
        String string = properties.getProperty("ID");
        System.out.println(string);

    }
}
