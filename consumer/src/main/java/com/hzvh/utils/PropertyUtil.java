package com.hzvh.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public  static Properties properties;

    static{
        InputStream is = ClassLoader.getSystemResourceAsStream("kafka_hbase.properties");
        try {
            properties  = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
