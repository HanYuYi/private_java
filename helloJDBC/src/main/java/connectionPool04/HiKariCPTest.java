package connectionPool04;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC连接池
 * 创建DataSource非常昂贵，通常DataSource实例总是作为一个全局变量存储，贯穿整个应用程序的生命周期
 */
public class HiKariCPTest {
    public static Connection hiKariCPConnection = null;

    /**
     * 封装连接 HiKariCP Connection
     */
    public static void connectionPool() {
        if (hiKariCPConnection == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/study?useSSL=false");
            config.setUsername("root");
            config.setPassword("password");
            // 连接超时
            config.addDataSourceProperty("connectionTimeout", "1000");
            // 空闲超时
            config.addDataSourceProperty("idleTimeout", "60000");
            // 最大连接数
            config.addDataSourceProperty("maximumPoolSize", "10");
            // 初始化HikariCP配置
            HikariDataSource hikariDataSource = new HikariDataSource(config);
            // 建立连接，相当于 DriverManager.getConnection
            try {
                Connection connection = hikariDataSource.getConnection();
                hiKariCPConnection = connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void select() {
        try {
            try (PreparedStatement preparedStatement = hiKariCPConnection.prepareStatement("SELECT * FROM students")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Students> dataList = new ArrayList<>();
                    while (resultSet.next()) {
                        // 将查询到的数据跟javaBean做映射
                        dataList.add(new Students(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("gender"),
                                resultSet.getInt("score"),
                                resultSet.getLong("class_id")
                        ));
                    }
                    for (Students s : dataList) {
                        System.out.println(s);
                    }
                }
            }
            hiKariCPConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connectionPool();
        select();
        // 待修复的bug，connection的开关
        // select();
    }
}
