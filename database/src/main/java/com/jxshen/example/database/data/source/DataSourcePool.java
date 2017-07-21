package com.jxshen.example.database.data.source;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * reference from: http://itlangzicn.iteye.com/blog/584577
 */
public class DataSourcePool implements DataSource {

    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static int minConn = 10;
    private static int maxConn = 100;
    private static List<Connection> list = new LinkedList<Connection>();
    static {
        try {
            InputStream in = DataSourcePool.class.getClassLoader().getResourceAsStream("DBConnection.properties");
            Properties props = new Properties();
            props.load(in);
            Class.forName(props.getProperty("driverClass"));  // 注册驱动
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    // 采用IoDH来获得单例
    private static final class InstanceHolder {
        private static DataSourcePool pool = new DataSourcePool();
    }
    
    // 初始化创建minConn个Connection
    private DataSourcePool() {
        for (int i = 0; i < minConn; i++) {
            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                list.add(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static DataSourcePool getInstance() {
        return InstanceHolder.pool;
    }
    
    // 释放所有池资源
    public synchronized void release() {
        for (Connection conn : list) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public synchronized Connection getConnection() {
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        
        final Connection conn = list.get(0);
        // 动态代理，返回一个代理对象
        Connection proxyConn = (Connection)Proxy.newProxyInstance(
                DataSourcePool.class.getClassLoader(),
                conn.getClass().getInterfaces(),
                new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        // 拦截Connection.close()方法，池化连接的close是将连接还给池
                        if (method.getName().equals("close")) {
                            synchronized(list) {
                                list.add(conn);
                                return null;
                            }
                        }
                        return method.invoke(conn, args);
                    }
        });
        return proxyConn;
    }
    
    public static void main(String[] args) {
        DataSourcePool pool = DataSourcePool.getInstance();
        Connection conn = pool.getConnection();
        System.out.println(conn);
        pool.release();
    }
    
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
