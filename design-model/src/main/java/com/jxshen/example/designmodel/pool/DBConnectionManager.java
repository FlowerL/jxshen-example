package com.jxshen.example.designmodel.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * instantiate function refere from : http://blog.csdn.net/zhuanshenweiliu/article/details/39274225
 */
public class DBConnectionManager {

    private DBConnectionManager(){};
    
    // IoDH way to get singleton
    private static final class InstanceHolder {
        private static final DBConnectionManager instance = new DBConnectionManager();
    }
    
    public DBConnectionManager getInstance() {
        return InstanceHolder.instance;
    }
    
    // 其他字段
    private static int clients; // 客户数量
    private List<Driver> drivers = new LinkedList<Driver>();  // 数据库驱动
    private Map<String, DBConnectionPool> pools = new HashMap<String, DBConnectionPool>(); // 键值对形式存取连接池对象
    
    public boolean freeConnection(String poolName, Connection conn) {
        DBConnectionPool pool = pools.get(poolName);
        if (pool == null) {
            return false;
        }
        
        return pool.freeConnection(conn);
    }
    
    public Connection getConnection(String poolName) {
        return getConnection(poolName, 0);
    }
    
    public Connection getConnection(String poolName, long timeout) {
        DBConnectionPool pool = pools.get(poolName);
        if (pool == null) {
            return null;
        }
        
        return pool.getConnection(timeout);
    }
    
    // 释放所有资源
    public synchronized void release() throws SQLException {
        for (Map.Entry<String, DBConnectionPool> entry : pools.entrySet()) {
            entry.getValue().release();
        }
    }
    
    // 根据属性文件提供的信息，创建一个或多个连接池
    private synchronized void createPools(Properties props) {
    }
    
    // 初始化连接池管理类的唯一实例，由私有构造函数调用  
    private void init(){}
    
    // 装载数据库驱动程序  
    private void loadDrivers(Properties props){}
}
