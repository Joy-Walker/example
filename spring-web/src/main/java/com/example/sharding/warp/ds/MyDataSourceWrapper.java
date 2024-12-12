package com.example.sharding.warp.ds;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Wrapper;
import java.util.logging.Logger;

public class MyDataSourceWrapper implements DataSource, Wrapper {

    private final DataSource delegate;

    public MyDataSourceWrapper(DataSource delegate) {
        this.delegate = delegate;
    }

    // 实现 DataSource 接口中所有方法
    @Override
    public Connection getConnection() throws SQLException {
        return delegate.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return delegate.getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return delegate.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        delegate.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        delegate.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return delegate.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return delegate.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return iface.cast(this);
        } else if (delegate instanceof Wrapper) {
            return ((Wrapper) delegate).unwrap(iface);
        } else {
            throw new SQLException("Unable to unwrap to " + iface.getName());
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this) || (delegate instanceof Wrapper && ((Wrapper) delegate).isWrapperFor(iface));
    }

    // 自定义扩展方法
    public void customMethod() {
        System.out.println("====customMethod====");
    }
}
