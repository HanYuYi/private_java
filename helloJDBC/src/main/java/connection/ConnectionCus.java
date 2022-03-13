package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 将连接封装
 */
public class ConnectionCus {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/study?useSSL=false";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "password";

    public java.sql.Connection start() {
        java.sql.Connection connection = null;
        try {
            // 建立连接
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
