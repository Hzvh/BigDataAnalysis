package com.hzvh.util;


import java.sql.Connection;
import java.sql.SQLException;

public class JDBCInstance {
    private  static Connection connection = null;

    private JDBCInstance(){

    }
    public static synchronized Connection getInstance() throws SQLException{
        if (connection == null || connection.isClosed() ){
            connection = JDBCUtil.getConnection();
        }
        return  connection;
    }
//|| !connection.isValid(3)
}
