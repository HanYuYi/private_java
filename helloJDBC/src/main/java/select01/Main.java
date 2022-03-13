package select01;

import connection.ConnectionCus;

import java.sql.*;

/**
 * JDBC 查询
 */
public class Main {

    private static void select(long id, String gender) {
        java.sql.Connection start = new ConnectionCus().start();

        try {
            // 建立连接
                // 查询语句，prepareStatement：防止sql注入攻击
                try (PreparedStatement statement = start.prepareStatement(
                        "SELECT id, name, score, class_id FROM students WHERE id = ? AND gender = ?"
                )) {
                     statement.setObject(1, id);
                     statement.setObject(2, gender);
                     // 查询
                    try (ResultSet resultSet = statement.executeQuery()) {
                        System.out.println("---------------------");
                        while (resultSet.next()) {
                            System.out.print(resultSet.getLong("id"));
                            System.out.print(" ");
                            System.out.print(resultSet.getString("name"));
                            System.out.print(" ");
                            System.out.print(resultSet.getInt("score"));
                            System.out.print(" ");
                            System.out.print(resultSet.getLong("class_id"));
                            System.out.println();
                            System.out.println("---------------------");
                        }
                    }
                }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        select(13, "F");
    }
}
