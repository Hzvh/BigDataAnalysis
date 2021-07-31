package com.hzvh.util;

import java.sql.*;

public class JDBCUtil {
    //private static final Logger logger = LoggerFactory.getLogger(JDBCUtil.class);
    private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://192.168.172.141:3306/ct?useUnicode=true&characterEncoding=UTF-8";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "123456";
    /**
     * 获取Mysql数据库的连接
     * @return
     */
    public static Connection getConnection(){
        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接释放资源
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet != null) try {
            resultSet.close();
        } catch (SQLException e) {
        }

        if(statement != null) try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(connection != null) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
