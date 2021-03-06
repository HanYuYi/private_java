--创建数据库
CREATE DATABASE IF NOT NUll EXISTS <数据库名称>;

--使用数据库
USE <数据库名称>;

--创建表
--AUTO_INCREMENT 自动增长
--常用数据类型 INT BIGINT REAL DOUBLE CHAR() VARCHAR() BOOLEAN DATE TIME DATETIME，
--具体参考：https://www.liaoxuefeng.com/wiki/1252599548343744/1321748435828770
CREATE TABLE <表名> (
    <字段名称> <数据类型> NOT NULL PRIMARY KEY (<字段名称1>, <字段名称2>) AUTO_INCREMENT,
    <字段名称> <数据类型> DEFAULT <字段默认值>,
    --设置主键
    PRIMARY KEY (<字段名称>)
) ENGINE=InnoDB DEFAULT CHARACTER=utf8;

CREATE TABLE <表名> (
    <字段名称> <数据类型>,
    --设置外键
    FOREIGN KEY (<字段名称>) REFERENCES <父表的名称(父表的字段)>
) ENGINE=InnoDB DEFAULT CHARACTER=utf8;

--字段唯一约束
ALTER TABLE <表名> ADD UNIQUE(字段名);

---创建登录用户（要先创建数据库）
CREATE USER IF NOT EXISTS <用户名>@'%' IDENTIFIED BY '<口令>';
---用户授权
GRANT ALL PRIVILEGES ON <数据库名>.* TO <用户名>@'%' WITH GRANT OPTION;
---刷新权限
FLUSH PRIVILEGES;