package com.example.sharding.warp.ds;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {

    @Override
    public Connection getConnection() throws SQLException {
        // 这里可以返回一个实际的数据库连接
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        // 这里可以返回一个带有用户名和密码的数据库连接
        return null;
    }

    // 实现其他 DataSource 方法...
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return iface.cast(this);
        } else {
            throw new SQLException("Unable to unwrap to " + iface.getName());
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this);
    }

    // 自定义方法
    public void customMethod() {
        System.out.println("====customMethod====");
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
