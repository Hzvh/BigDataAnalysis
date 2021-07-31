package com.hzvh.utils;

import com.hzvh.constants.Constant;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class ConnectionInstance {
    private static Connection connection;

    private ConnectionInstance(){

    }
    public static Connection getInstance(){
        if (connection==null || connection.isClosed()){
            try {
                connection = ConnectionFactory.createConnection(Constant.CONF);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
