package shiwu03;

import connection.ConnectionCus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC 事务（执行一段sql）
 */
public class Main {

    /**
     * 事务第一条操作
     * @param id
     * @param score
     */
    private static void descScore(long id, int score) {
        java.sql.Connection start = new ConnectionCus().start();
        try {
            try (PreparedStatement preparedStatement = start.prepareStatement("UPDATE students SET score = score - ? WHERE id = ?")) {
                preparedStatement.setObject(1, score);
                preparedStatement.setObject(2, id);
                preparedStatement.executeUpdate();
            }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务第二条操作
     * @param id
     * @param score
     */
    private static void addScore(long id, int score) {
        java.sql.Connection start = new ConnectionCus().start();
        try {
            try (PreparedStatement preparedStatement = start.prepareStatement("UPDATE students SET score = score + ? WHERE id = ?")) {
                preparedStatement.setObject(1, score);
                preparedStatement.setObject(2, id);
                preparedStatement.executeUpdate();
            }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务操作（重点）
     */
    private static void handle() {
        try {
            java.sql.Connection start = new ConnectionCus().start();
            try {
                start.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                // 关闭自动提交
                start.setAutoCommit(false);
                int score = 5;
                descScore(2, score);
                addScore(7, score);
                start.commit();
            } catch (SQLException e) {
                // 失败了就回滚事务
                start.rollback();
            } finally {
                // 恢复自动提交
                start.setAutoCommit(true);
                start.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void select(long id) {
        java.sql.Connection start = new ConnectionCus().start();
        try {
            try (PreparedStatement preparedStatement = start.prepareStatement("SELECT * FROM students WHERE id = ?")) {
                preparedStatement.setObject(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.print(resultSet.getLong("id"));
                        System.out.print(" ");
                        System.out.print(resultSet.getInt("score"));
                    }
                }
            }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        handle();

        select(2);
        System.out.println();
        System.out.println("--------");
        select(7);
    }
}
