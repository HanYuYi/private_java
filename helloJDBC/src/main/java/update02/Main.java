package update02;

import connection.ConnectionCus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * 定义插入的数据结构
 */
class Temp {
    public long class_id;
    public String name;
    public String gender;
    public int score;

    public Temp(long class_id, String name, String gender, int score) {
        this.class_id = class_id;
        this.name = name;
        this.gender = gender;
        this.score = score;
    }
}

/**
 * JDBC 插入，更新，删除
 * 操作之后必须更更新 executeUpdate
 */
public class Main {

    private static ArrayList<update02.Temp> list = null;

    /**
     * 查询所有数据
     */
    public static void select() {
        try {
            // 定义JDBC连接
            java.sql.Connection start = new ConnectionCus().start();
            try (PreparedStatement statement = start.prepareStatement("SELECT * FROM students")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("-----------------------------");
                    while (resultSet.next()) {
                        System.out.print(" ｜ ");
                        System.out.print(resultSet.getLong("id"));
                        System.out.print(" ｜ ");
                        System.out.print(resultSet.getString("name"));
                        System.out.print(" ｜ ");
                        System.out.print(resultSet.getInt("score"));
                        System.out.print(" ｜ ");
                        System.out.print(resultSet.getLong("class_id"));
                        System.out.print(" ｜ ");
                        System.out.println();
                        System.out.println("-----------------------------");
                    }
                }
            }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造插入数据
     * @param num
     * @return
     */
    private static ArrayList<update02.Temp> backList (int num) {
        ArrayList<Temp> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new Temp(i + 2, "小明" + (i + 1), i % 2 == 0 ? "F" : "M", (i + 1) * 30));
        }
        return list;
    }

    /**
     * 利用StringJoiner构造插入sql
     * @return
     */
    /*private static String constructSql() {
        String sqlStr = "INSERT INTO students (class_id, name, gender, score) VALUES (";
        StringJoiner stringJoiner = new StringJoiner(", ", "", ")");
        for (int i = 0; i < list.size(); i++) {
            stringJoiner.add("?");
        }
        sqlStr += stringJoiner;
        return sqlStr;
    }*/

    /**
     * 插入多条数据，其实插入多应该用batch实现，这里只是为了演示
     */
    private static void insert() {
        try {
            // 定义JDBC连接
            java.sql.Connection start = new ConnectionCus().start();
            for (int i = 0; i < list.size(); i++) {
                try (PreparedStatement statement = start.prepareStatement(
                        "INSERT INTO students (class_id, name, gender, score) VALUES (?, ?, ?, ?)",
                        // 有自增主键的情况下要注意 1：必须指定一个标志位，但自增会基于上次的主键自增，即使被删除了
                        Statement.RETURN_GENERATED_KEYS
                )) {
                    statement.setObject(1, list.get(i).class_id);
                    statement.setObject(2, list.get(i).name);
                    statement.setObject(3, list.get(i).gender);
                    statement.setObject(4, list.get(i).score);
                    statement.executeUpdate();
                    // 有自增主键的情况下要注意 2：必须调用，表示JDBC驱动必须返回插入的自增对象
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            // 返回插入的索引
                            System.out.println(generatedKeys.getLong(1));
                        }
                    }
                }
            }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入多条数据，用batch实现
     */
    private static void batchInsert() {
        try {
            java.sql.Connection start = new ConnectionCus().start();
            try (PreparedStatement preparedStatement = start.prepareStatement("INSERT INTO students (class_id, name, gender, score) VALUES (?, ?, ?, ?)")) {
                for (Temp item : list) {
                    preparedStatement.setLong(1, item.class_id);
                    preparedStatement.setString(2, item.name);
                    preparedStatement.setString(3, item.gender);
                    preparedStatement.setInt(4, item.score);
                    preparedStatement.addBatch();
                    int[] ints = preparedStatement.executeBatch();
                    for (int n : ints) {
                        System.out.println(n);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据
     * @param id
     * @param score
     */
    public static void update(long id, int score) {
        try {
            // 定义JDBC连接
            java.sql.Connection start = new ConnectionCus().start();
            try (PreparedStatement preparedStatement = start.prepareStatement("UPDATE students SET score = ? WHERE id = ?")) {
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
     * 删除数据
     * @param id
     */
    public static void delete(long id) {
        try {
            java.sql.Connection start = new ConnectionCus().start();
            try (PreparedStatement preparedStatement = start.prepareStatement("DELETE FROM students WHERE id = ?")) {
                preparedStatement.setObject(1, id);
                preparedStatement.executeUpdate();
            }
            start.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        list = backList(4);

        // insert();

        batchInsert();

        update(9, 83);

        delete(15);

        select();
    }
}
