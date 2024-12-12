package com.example.sharding.warp.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * 适配器，自己的underConnection没有实现connection
 * 所以搞了个适配器，有时候需要拿到这个更底层的对象，所有再搞了个wrap
 * wrap包装嘛，unwrap就是拿到被包装的对象
 * @author :panligang
 * @description :
 * @create :2024-12-05 19:07:00
 */
public class Client {

    public static void main(String[] args) throws SQLException {

        Connection underConnection = new MyConnection();
        Connection connection = new MyConnection(underConnection);

        if (connection.isWrapperFor(MyConnection.class)) {
            MyConnection myConnection = connection.unwrap(MyConnection.class);
            myConnection.customMethod();
        }

    }
}
