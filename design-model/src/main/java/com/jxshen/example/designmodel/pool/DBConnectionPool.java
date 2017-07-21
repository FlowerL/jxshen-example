package com.jxshen.example.designmodel.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * instantiate function refere from : http://blog.csdn.net/zhuanshenweiliu/article/details/39274225
 */
public class DBConnectionPool {

    private int checkedOut;  // 已经被分配出去的连接数 
    private List<Connection> freeConnections = new LinkedList<Connection>(); // 容器，空闲池
    private int minConn;  // 连接池连接的最小数量
    private int maxConn;  // 连接池连接的最大数量
    private String poolName;  // 连接池名字，方便连接池管理类管理
    private String url;   // 数据库地址
    private String username;  // 数据库用户名
    private String password;  // 数据库密码
    
    // 公开的构造函数，给连接池管理类调用
    public DBConnectionPool(String poolName, String url, String username, String password, int maxConn) {
        this.poolName = poolName;
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConn = maxConn;
    }
    
    // 使用完毕，把连接返回给空闲池
    public synchronized boolean freeConnection(Connection conn) {
        if (checkedOut <= 0) {
            return false;
        }
        checkedOut--;
        freeConnections.add(conn);
        notifyAll();
        return true;
    }
    
    // 从空闲池获取一个可用连接
    public synchronized Connection getConnection(long timeout) {
        try {
            while (freeConnections.size() == 0) {
                wait(timeout);
            }
        } catch (InterruptedException e) {
            return null; 
        }
        Connection conn = freeConnections.get(0);  // 按创建时间顺序
        freeConnections.remove(0);
        checkedOut++;
        
        return conn;
    }
    
    // 断开所有连接，释放系统资源
    public synchronized void release() throws SQLException {
        for (Connection conn : freeConnections) {
            conn.close();
        }
    }
    
    // 新建一个数据库连接，私有函数，内部用
    private Connection newConnection() throws SQLException {
        if (checkedOut + freeConnections.size() >= maxConn) {
            return null;
        }
        
        // DataSource本来就是池化资源，这里用DriverManager直接建立与数据库的连接
        Connection conn = DriverManager.getConnection(url, username, password);
        if (conn == null) {
            return null;
        }
        
        if (freeConnections.add(conn)) {
            return conn;
        }
        else {
            return null;
        }
    }
}
