package com.jxshen.example.database.driver.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * reference from: http://itlangzicn.iteye.com/blog/584577
 */
public class DriverManagerConnection {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static Connection conn = null;
    static {
        try {
            Class.forName(DRIVER_CLASS); // 注册驱动，并运行其静态代码块
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return conn;
    }
    
    public static void main(String[] args) throws SQLException {
        DriverManagerConnection dmc = new DriverManagerConnection();
        System.out.println(dmc.getConnection());
        dmc.getConnection().close();
        return;
    }
}
