package myAOP.utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接工具类，用于从数据库中获取连接，实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static BasicDataSource dataSource = new BasicDataSource();

    //设置数据库连接参数
    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    //获取当前线程上的连接
    public Connection getThreadConnection() {
        //取出当前线程连接
        Connection conn = threadLocal.get();
        //当前线程无连接
        if (conn == null) {
            //从数据源获取连接，存入ThreadLocal
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocal.set(conn);
        }

        //返回当前线程的连接
        return conn;
    }

    //把连接和线程解绑
    public void removeConnection() {
        threadLocal.remove();
    }
}
