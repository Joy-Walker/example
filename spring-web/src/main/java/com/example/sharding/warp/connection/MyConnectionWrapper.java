package com.example.sharding.warp.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;

/**
 *
 * @author :panligang
 * @description :
 * @create :2024-12-05 18:53:00
 */
public class MyConnectionWrapper implements Wrapper {



    public MyConnectionWrapper() {
    }



    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {

        if(this.isWrapperFor(iface)) {
            return iface.cast(this);
        }

        throw new SQLException("not a MyConnection");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface == MyConnection.class;
    }
}
