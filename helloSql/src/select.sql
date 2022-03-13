---查询mysql端口
SHOW GLOBAL VARIABLES LIKE 'port';

---查询表字段
SHOW FULL COLUMNS FROM <表名>;
--查询表字段
DESC <表名>;
--查看创建表的SQL语句
SHOW CREATE TABLE <表名>;

---查询表数据
SELECT * FROM <表名> WHERE <表达式>;
--条件查询 and
SELECT * FROM <表名> WHERE <表达式1> AND <表达式2>;
--条件查询 or
SELECT * FROM <表名> WHERE <表达式1> OR <表达式2>;
--条件查询 not
--条件查询 <> 代表不等于
SELECT * FROM <表名> WHERE NOT <表达式>;
SELECT * FROM <表名> WHERE <字段 <> 值>;

---相似
SELECT * FROM <表名> WHERE <字段名> LIKE '%<相似字符>';
SELECT * FROM <表名> WHERE <字段名> LIKE '%<相似字符>%';

---投影查询
SELECT <字段1>, <字段2> FROM <表名>;

---投影查询取别名
SELECT <字段1> <别名1>, <字段2> <别名2> FROM <表名>;

---排序查询 升序ASC(默认)
SELECT * FROM <表名> ORDER BY <排序字段>;
--排序查询 降序DESC
SELECT * FROM <表名> ORDER BY <排序字段> DESC;
--排序查询 升序，多个字段查询
SELECT * FROM <表名> ORDER BY <排序字段1>, <排序字段2>;

---分页查询
--OFFSET计算公式：pageIndex = pageSize * pageNumber
SELECT * FROM <表名> LIMIT <pageSize> OFFSET <pageIndex>;
--分页查询 简写
SELECT * FROM <表名> LIMIT <pageIndex>, <pageSize>;
--分页查询 查询页数
SELECT CEILING(COUNT(*) / <pageSize>) FROM <表名>;

---聚合查询 COUNT() SUM() AVG() MAX() MIN()
--COUNT() 查询数据条数
SELECT COUNT(*) FROM <表名>;
SELECT COUNT(*) <别名> FROM <表名>;
SELECT COUNT(id) <别名> FROM <表名> WHERE <表达式>;
--SUM() 计算某一列的合计值
SELECT SUM(<字段>) FROM <表名>;
--AVG() 计算某一列的平均值
SELECT SUM(<字段>) FROM <表名>;
--分组聚合 可以理解为：根据某个，或某几个字段将数据分为几组
SELECT <字段>, COUNT(*) <别名> FROM <表名> GROUP BY <字段>;
SELECT <字段>, AVG(*) <别名> FROM <表名> GROUP BY <字段>;
SELECT <字段1>, <字段2>, AVG(*) <别名> FROM <表名> GROUP BY <字段1>, <字段2>;
SELECT <字段1>, <字段2>, AVG(*) <别名> FROM <表名> WHERE <表达式> GROUP BY <字段1>, <字段2>;

---多表查询
SELECT * FROM <表名1>, <表名2>;
--多表查询，因为有重名的字段，所以要投影别名
SELECT <表名1.字段1> <别名>, <表名1.字段2> <别名>, <表名1.字段1> <别名> FROM <表名1>, <表名2>;
--多表查询，因为有重名的字段，所以要投影别名，也可以给表使用别名
SELECT <表名1的别名.字段1> <别名>, <表名1的别名.字段2> <别名>, <表名2的别名.字段1> <别名>
    FROM <表名1> <表名1的别名>, <表名2> <表名2的别名>;
--多表查询 也可以使用WHERE条件
SELECT <表名1的别名.字段1> <别名>, <表名1的别名.字段2> <别名>, <表名2的别名.字段1> <别名>
    FROM <表名1> <表名1的别名>, <表名2> <表名2的别名>
    WHERE <表名1的别名.字段> = XXX;

---连接查询
--INNER JOIN 内连接
SELECT <表名1的别名.字段1> <别名>, <表名2的别名.字段1> <别名>
    FROM <表名1> <表名1的别名>
    INNER JOIN <表名2> <表名2的别名>
    ON <表名2的别名.字段> = <表名1的别名.字段>
    WHERE <表达式>;
--RIGHT OUTER JOIN 会查询到表2为null的数据
SELECT <表名1的别名.字段>, <表名2的别名.字段>
    FROM <表名1> <表名1的别名>
    RIGHT OUTER JOIN <表名2> <表名2的别名>
    ON <表达式>;
--LEFT OUTER JOIN 会查询到表1为null的数据
SELECT <表名1的别名.字段>, <表名2的别名.字段>
    FROM <表名1> <表名1的别名>
    LEFT OUTER JOIN <表名2> <表名2的别名>
    ON <表达式>;
--FULL OUTER JOIN 会查询到表1和表2为null的数据，UNION 也可以实现
SELECT <表名1的别名.字段>, <表名2的别名.字段>
    FROM <表名1> <表名1的别名>
    FULL OUTER JOIN <表名2> <表名2的别名>
    ON <表达式>;

---多个条件组合
SELECT <字段1> <别名1>, <字段2> <别名2> FROM <表名>
    WHERE (<表达式1> OR <表达式2>) AND <表达式1>
    ORDER BY <排序字段1>, <排序字段2>
    LIMIT <pageSize> OFFSET <pageIndex>;