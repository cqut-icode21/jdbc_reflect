package com.baoxiangjie.mysql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC工具类
 * @author BaoXiangjie
 */
public class DatabaseUtils {

    /**
     * 加载数据库驱动并连接数据库
     * @return 数据库连接
     */
    public static Connection getConnection() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/teachers?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT";
        String userName = "root";
        String password = "bxjsql";
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param resultSet 结果集
     * @param statement 预加载
     * @param connection 数据库连接
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
