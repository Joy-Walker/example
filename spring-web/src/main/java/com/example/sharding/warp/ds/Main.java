package com.example.sharding.warp.ds;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // 创建 MyDataSource 对象
            MyDataSource myDataSource = new MyDataSource();

            // 创建 MyDataSourceWrapper 对象，包装 MyDataSource
            MyDataSourceWrapper myDataSourceWrapper = new MyDataSourceWrapper(myDataSource);

            // 使用包装后的数据源获取连接
            Connection connection = myDataSourceWrapper.getConnection();
            if (connection != null) {
                System.out.println("Connection obtained successfully.");
            }

            // 使用自定义扩展方法
            myDataSourceWrapper.customMethod();

            // 检查是否可以解开为 MyDataSourceWrapper
            if (myDataSourceWrapper.isWrapperFor(MyDataSourceWrapper.class)) {
                MyDataSourceWrapper unwrapped = myDataSourceWrapper.unwrap(MyDataSourceWrapper.class);
                unwrapped.customMethod();
            }

            // 关闭连接
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
